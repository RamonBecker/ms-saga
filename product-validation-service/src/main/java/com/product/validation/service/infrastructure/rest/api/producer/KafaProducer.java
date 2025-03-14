package com.product.validation.service.infrastructure.rest.api.producer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.product.validation.service.infrastructure.shared.constants.TopicKafkaConfig.ORCHESTRATOR;

@Slf4j
@Component
public class KafaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafaProducer(KafkaTemplate<String, String> kafkaTemplate) {
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
