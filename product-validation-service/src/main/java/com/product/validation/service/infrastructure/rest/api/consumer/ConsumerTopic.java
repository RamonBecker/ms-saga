package com.product.validation.service.infrastructure.rest.api.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.product.validation.service.infrastructure.dto.event.EventDTO;
import com.product.validation.service.infrastructure.rest.api.product.SendProduct;
import com.product.validation.service.infrastructure.shared.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ConsumerTopic {


    private final JsonSerializer serializer;
    private final SendProduct send;

    public ConsumerTopic(JsonSerializer jsonSerializer, SendProduct send) {
        this.serializer = jsonSerializer;
        this.send = send;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-success}"
    )
    public void consumeSuccessEvent(String payload) throws JsonProcessingException {
        log.info("Received success event {} from product-validation-success topic ", payload);

        var event = serializer.fromJson(payload, EventDTO.class);

        log.info(event.toString());

        send.success(event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-fail}"
    )
    public void consumeFailEvent(String payload) throws JsonProcessingException {
        log.info("Received rollback event {} from product-validation-fail topic ", payload);

        var event = serializer.fromJson(payload, EventDTO.class);

        log.info(event.toString());

        send.rollback(event);

    }

}
