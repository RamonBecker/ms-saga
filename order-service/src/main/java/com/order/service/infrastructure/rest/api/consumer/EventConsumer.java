package com.order.service.infrastructure.rest.api.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.service.core.domain.Event;
import com.order.service.infrastructure.data.db.entities.EventEntity;
import com.order.service.infrastructure.data.db.repositories.impl.EventRepository;
import com.order.service.infrastructure.shared.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventConsumer {


    private JsonSerializer jsonSerializer;
    private EventRepository service;

    public EventConsumer() {}

    public EventConsumer(JsonSerializer jsonSerializer, EventRepository service) {
        this.jsonSerializer = jsonSerializer;
        this.service = service;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.notify-ending}"
    )

    public void notifyEndingEvent(String payload) throws JsonProcessingException {
        log.info("Received ending notification event {} from notify-ending topic ", payload);
        service.notifyEnding(Event.from(jsonSerializer.fromJson(payload, EventEntity.class)));
    }

}
