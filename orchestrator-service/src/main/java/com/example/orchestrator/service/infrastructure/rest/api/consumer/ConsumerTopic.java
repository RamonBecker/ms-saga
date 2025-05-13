package com.example.orchestrator.service.infrastructure.rest.api.consumer;


import com.example.orchestrator.service.infrastructure.dto.event.EventDTO;
import com.example.orchestrator.service.infrastructure.saga.usecases.SagaEventHandler;
import com.example.orchestrator.service.infrastructure.serializers.JsonSerializer;
import com.example.orchestrator.service.infrastructure.shared.constants.Topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.orchestrator.service.infrastructure.shared.constants.Topic.*;


@Slf4j
@Component
public class ConsumerTopic {

    private final JsonSerializer jsonSerializer;
    private final SagaEventHandler sagaEventHandler;

    public ConsumerTopic(JsonSerializer jsonSerializer, SagaEventHandler sagaEventHandler) {
        this.jsonSerializer = jsonSerializer;
        this.sagaEventHandler = sagaEventHandler;
    }


    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.start-saga}"
    )
    public void onStart(String payload) throws Exception {
        handle(payload, START_SAGA);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.orchestrator}"
    )
    public void onContinue(String payload) throws Exception {
        handle(payload, CONTINUE_SAGA);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.finish-success}"
    )
    public void onFinishSuccess(String payload) throws Exception {
        handle(payload, FINISH_SUCCESS);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.finish-fail}"
    )
    public void onFinishFail(String payload) throws Exception {
        handle(payload, FINISH_FAIL);
    }

    private void handle(String payload, Topic topic) throws Exception {
        log.info("Kafka Event received on [{}]: {}", topic, payload);
        sagaEventHandler.handle(topic, jsonSerializer.fromJson(payload, EventDTO.class));
    }

}
