package com.order.service.infrastructure.rest.api.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.service.infrastructure.data.db.converters.EventConverter;
import com.order.service.infrastructure.data.db.entities.EventEntity;
import com.order.service.infrastructure.data.db.repositories.impl.EventService;
import com.order.service.infrastructure.shared.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventConsumer {


    private final JsonSerializer jsonSerializer;
    private final EventService eventService;
    private final EventConverter eventConverter;

    public EventConsumer(JsonSerializer jsonSerializer, EventService eventService, EventConverter eventConverter) {
        this.jsonSerializer = jsonSerializer;
        this.eventService = eventService;
        this.eventConverter = eventConverter;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.notify-ending}"
    )
    public void notifyEndingEvent(String payload) throws JsonProcessingException {
        log.info("Received ending notification event {} from notify-ending topic ", payload);
        var event = jsonSerializer.fromJson(payload, EventEntity.class);

        eventService.notifyEnding(eventConverter.eventEntityToEvent(event));
    }

}
