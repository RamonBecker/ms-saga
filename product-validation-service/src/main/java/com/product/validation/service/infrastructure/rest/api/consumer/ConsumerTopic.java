package com.product.validation.service.infrastructure.rest.api.consumer;


import com.product.validation.service.core.domain.event.Event;
import com.product.validation.service.infrastructure.dto.event.EventDTO;
import com.product.validation.service.infrastructure.rest.api.consumer.usecases.ConsumeEventHandler;
import com.product.validation.service.infrastructure.rest.api.serializers.JsonSerializer;
import com.product.validation.service.infrastructure.shared.constants.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ConsumerTopic {

    private final JsonSerializer serializer;
    private final ConsumeEventHandler handler;

    public ConsumerTopic(JsonSerializer jsonSerializer, ConsumeEventHandler handler) {
        this.serializer = jsonSerializer;
        this.handler = handler;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-success}"
    )
    public void onSuccess(String payload) throws Exception {
        handle(Status.SUCCESS, payload);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-fail}"
    )
    public void onFail(String payload) throws Exception {
        handle(Status.FAIL, payload);
    }

    private void handle(Status status, String payload) throws Exception {

        var dto = serializer.fromJson(payload, EventDTO.class);
        var event = Event.fromDomain(dto);

        log.info("Received {} event {} from product-validation-fail topic: ", status, payload);
        handler.handle(status, event);
    }
}
