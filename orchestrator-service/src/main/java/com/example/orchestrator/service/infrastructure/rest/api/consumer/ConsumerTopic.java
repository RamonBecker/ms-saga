package com.example.orchestrator.service.infrastructure.rest.api.consumer;


import com.example.orchestrator.service.infrastructure.responses.EventResponse;
import com.example.orchestrator.service.infrastructure.shared.JsonSerializer;
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
            topics = "${spring.kafka.topic.start-saga}"
    )
    public void consumeStartSagaEvent(String payload) throws JsonProcessingException {
        log.info("Received  event {} from start-saga topic ", payload);

        var event = jsonSerializer.fromJson(payload, EventResponse.class);

        log.info(event.toString());
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.orchestrator}"
    )
    public void consumeOrchestratorEvent(String payload) throws JsonProcessingException {
        log.info("Received  event {} from orchestrator topic ", payload);

        var event = jsonSerializer.fromJson(payload, EventResponse.class);

        log.info(event.toString());
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.finish-success}"
    )
    public void consumeFinishSuccessEvent(String payload) throws JsonProcessingException {
        log.info("Received  event {} from finish-success topic ", payload);

        var event = jsonSerializer.fromJson(payload, EventResponse.class);

        log.info(event.toString());
    }

}
