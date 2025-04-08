package com.payment.service.infrastructure.rest.api.consumer;


import com.payment.service.infrastructure.dto.event.EventDTO;
import com.payment.service.infrastructure.rest.api.payment.SendPayment;
import com.payment.service.infrastructure.shared.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ConsumerTopic {

    private final JsonSerializer jsonSerializer;
    private final SendPayment send;

    public ConsumerTopic(JsonSerializer jsonSerializer, SendPayment send) {
        this.jsonSerializer = jsonSerializer;
        this.send = send;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.payment-success}"
    )
    public void consumeSuccessEvent(String payload) throws Exception {
        log.info("Received success event {} from payment-success topic ", payload);

        var event = jsonSerializer.fromJson(payload, EventDTO.class);

        log.info(event.toString());

        send.success(event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.payment-fail}"
    )
    public void consumeFailEvent(String payload) throws Exception {
        log.info("Received rollback event {} from payment-fail topic ", payload);

        var event = jsonSerializer.fromJson(payload, EventDTO.class);

        log.info(event.toString());

        send.rollback(event);
    }

}
