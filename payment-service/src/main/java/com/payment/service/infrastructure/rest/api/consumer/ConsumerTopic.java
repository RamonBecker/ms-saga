package com.payment.service.infrastructure.rest.api.consumer;


import com.payment.service.core.domain.event.Event;
import com.payment.service.infrastructure.dto.event.EventDTO;
import com.payment.service.infrastructure.rest.api.consumer.usecases.ConsumeEventHandler;
import com.payment.service.infrastructure.serializers.JsonSerializer;
import com.payment.service.infrastructure.shared.constants.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ConsumerTopic {

    private final JsonSerializer jsonSerializer;
    private final ConsumeEventHandler handler;

    public ConsumerTopic(JsonSerializer jsonSerializer, ConsumeEventHandler handler) {
        this.jsonSerializer = jsonSerializer;
        this.handler = handler;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.payment-success}"
    )
    public void onSuccess(String payload) throws Exception {
        handle(payload, Status.SUCCESS);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.payment-fail}"
    )
    public void onFail(String payload) throws Exception {
        handle(payload, Status.FAIL);
    }

    private void handle(String payload, Status status) throws Exception {
        log.info("Received {} event {} from payment-fail ", status, payload);
        var dto = jsonSerializer.fromJson(payload, EventDTO.class);
        var event = Event.fromDomain(dto);
        handler.handle(status, event);
    }

}
