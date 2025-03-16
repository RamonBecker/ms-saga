package com.example.inventory.service.infrastructure.rest.api.producer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.inventory.service.infrastructure.shared.constants.TopicKafkaConfig.ORCHESTRATOR;


@Slf4j
@Component
public class ProducerTopic {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerTopic(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String payload) {
        try {
            log.info("Sending event to topic {} with data: {}", ORCHESTRATOR, payload);
            kafkaTemplate.send(ORCHESTRATOR, payload);
        } catch (Exception e) {
            log.error("Error trying to send to topic {} with data: {}", payload, ORCHESTRATOR);

        }
    }
}
