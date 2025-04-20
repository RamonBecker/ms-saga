package com.example.inventory.service.infrastructure.rest.api.consumer;


import com.example.inventory.service.infrastructure.dto.EventDTO;
import com.example.inventory.service.infrastructure.rest.api.inventory.SendInventory;
import com.example.inventory.service.infrastructure.serializers.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ConsumerTopic {

    private final JsonSerializer jsonSerializer;
    private final SendInventory sendInventory;

    public ConsumerTopic(JsonSerializer jsonSerializer, SendInventory sendInventory) {
        this.jsonSerializer = jsonSerializer;
        this.sendInventory = sendInventory;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.inventory-success}"
    )
    public void consumeSuccessEvent(String payload) throws Exception {

        log.info("Received success event {} from inventory-success topic ", payload);

        var event = jsonSerializer.fromJson(payload, EventDTO.class);

        log.info(event.toString());

        sendInventory.success(event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.inventory-fail}"
    )
    public void consumeFailEvent(String payload) throws Exception {

        log.info("Received rollback event {} from inventory-fail topic ", payload);

        var event = jsonSerializer.fromJson(payload, EventDTO.class);

        log.info(event.toString());

        sendInventory.rollback(event);
    }

}
