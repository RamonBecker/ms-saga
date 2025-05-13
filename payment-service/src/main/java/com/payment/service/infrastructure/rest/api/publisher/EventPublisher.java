package com.payment.service.infrastructure.rest.api.publisher;

import com.payment.service.core.domain.event.Event;
import com.payment.service.infrastructure.rest.api.producer.ProducerTopic;
import com.payment.service.infrastructure.serializers.JsonSerializer;

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
