package com.example.orchestrator.service.infrastructure.rest.api.publisher;

import com.example.orchestrator.service.infrastructure.dto.event.EventDTO;
import com.example.orchestrator.service.infrastructure.rest.api.producer.ProducerTopic;
import com.example.orchestrator.service.infrastructure.serializers.JsonSerializer;

public class EventPublisher {

    private final JsonSerializer serializer;
    private final ProducerTopic producer;

    public EventPublisher(JsonSerializer serializer, ProducerTopic producer) {
        this.serializer = serializer;
        this.producer = producer;
    }

    public void publish(EventDTO event, String topic) throws Exception {
        producer.send(serializer.toJson(event), topic);
    }
}
