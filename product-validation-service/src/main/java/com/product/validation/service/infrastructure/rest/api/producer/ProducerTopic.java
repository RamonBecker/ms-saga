package com.product.validation.service.infrastructure.rest.api.producer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ProducerTopic {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerTopic(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String payload, String topic) {
        try {
            log.info("Sending event to topic {} with data: {}", topic, payload);
            kafkaTemplate.send(topic, payload);
        } catch (Exception e) {
            log.error("Error trying to send to topic {} with data: {}", payload, topic);

        }
    }
}
