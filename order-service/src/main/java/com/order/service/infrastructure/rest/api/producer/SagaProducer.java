package com.order.service.infrastructure.rest.api.producer;


import static com.order.service.infrastructure.shared.constants.TopicKafkaConfig.START_SAGA;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SagaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SagaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String payload) {
        try {
            log.info("Sending event to topic {} with data: {}", START_SAGA, payload);
            kafkaTemplate.send(START_SAGA, payload);
        } catch (Exception e) {
            log.error("Error trying to send to topic {} with data: {}", payload, START_SAGA);

        }
    }
}
