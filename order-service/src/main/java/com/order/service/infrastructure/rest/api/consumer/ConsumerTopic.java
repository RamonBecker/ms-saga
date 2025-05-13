package com.order.service.infrastructure.rest.api.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.service.core.domain.event.Event;
import com.order.service.core.usecases.notify.NotifyEnd;
import com.order.service.infrastructure.data.db.entities.EventEntity;
import com.order.service.infrastructure.rest.api.serializers.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerTopic {

    private final JsonSerializer jsonSerializer;
    private final NotifyEnd notifyEnd;

    public ConsumerTopic(JsonSerializer jsonSerializer, NotifyEnd notifyEnd) {
        this.jsonSerializer = jsonSerializer;
        this.notifyEnd = notifyEnd;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.notify-ending}"
    )

    public void onNotifyEnding(String payload) throws JsonProcessingException {

        log.info("Received ending notification event {} from notify-ending topic ", payload);

        var event = Event.fromEntity(jsonSerializer.fromJson(payload, EventEntity.class));

        notifyEnd.execute(event);

        log.info("Order {} with saga notified! TransactionId: {}", event.getOrderId(), event.getTransactionId());
    }
}
