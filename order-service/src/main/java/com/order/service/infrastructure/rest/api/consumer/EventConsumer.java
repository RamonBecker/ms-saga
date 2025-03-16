package com.order.service.infrastructure.rest.api.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.service.infrastructure.response.EventResponse;
import com.order.service.infrastructure.shared.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventConsumer {


    private final JsonSerializer jsonSerializer;

    public EventConsumer(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.notify-ending}"
    )
    public void notifyEndingEvent(String payload) throws JsonProcessingException {
        log.info("Received ending notification event {} from notify-ending topic ", payload);
        var event = jsonSerializer.fromJson(payload, EventResponse.class);
        log.info(event.toString());
    }

}
