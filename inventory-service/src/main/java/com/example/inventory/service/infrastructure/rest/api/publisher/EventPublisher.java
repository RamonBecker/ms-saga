package com.example.inventory.service.infrastructure.rest.api.publisher;

import com.example.inventory.service.core.domain.event.Event;
import com.example.inventory.service.infrastructure.rest.api.producer.ProducerTopic;
import com.example.inventory.service.infrastructure.serializers.JsonSerializer;

public class EventPublisher {

    private final JsonSerializer serializer;
    private final ProducerTopic producer;

    public EventPublisher(JsonSerializer serializer, ProducerTopic producer) {
        this.serializer = serializer;
        this.producer = producer;
    }

    public void publish(Event event) throws Exception {
        producer.send(serializer.toJson(event));
    }
}
