package com.example.inventory.service.infrastructure.rest.api.consumer;


import com.example.inventory.service.core.domain.event.Event;
import com.example.inventory.service.infrastructure.dto.EventDTO;
import com.example.inventory.service.infrastructure.rest.api.consumer.usecases.ConsumeEventHandler;
import com.example.inventory.service.infrastructure.serializers.JsonSerializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.inventory.service.infrastructure.shared.constants.Status;


import static com.example.inventory.service.infrastructure.shared.constants.Status.SUCCESS;
import static com.example.inventory.service.infrastructure.shared.constants.Status.FAIL;


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
            topics = "${spring.kafka.topic.inventory-success}"
    )
    public void onSuccess(String payload) throws Exception {
        handle(payload, SUCCESS);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.inventory-fail}"
    )
    public void onFail(String payload) throws Exception {
        handle(payload, FAIL);
    }

    private void handle(String payload, Status status) throws Exception {


        log.info("Received {} event {} from inventory topic ", status, payload);

        var dto = jsonSerializer.fromJson(payload, EventDTO.class);
        var event = Event.fromDomain(dto);

        handler.handle(status, event);

    }

}
