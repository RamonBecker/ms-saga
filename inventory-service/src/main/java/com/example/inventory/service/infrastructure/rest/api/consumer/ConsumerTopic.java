package com.example.inventory.service.infrastructure.rest.api.consumer;


import com.example.inventory.service.infrastructure.response.EventResponse;
import com.example.inventory.service.infrastructure.shared.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ConsumerTopic {

    private final JsonSerializer jsonSerializer;

    public ConsumerTopic(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.inventory-success}"
    )
    public void consumeSuccessEvent(String payload) throws JsonProcessingException {
        log.info("Received success event {} from inventory-success topic ", payload);

        var event = jsonSerializer.fromJson(payload, EventResponse.class);

        log.info(event.toString());
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.inventory-fail}"
    )
    public void consumeFailEvent(String payload) throws JsonProcessingException {
        log.info("Received rollback event {} from inventory-fail topic ", payload);

        var event = jsonSerializer.fromJson(payload, EventResponse.class);

        log.info(event.toString());
    }

}
