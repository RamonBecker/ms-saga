package com.payment.service.infrastructure.rest.api.producer;


import com.payment.service.infrastructure.config.kafka.KafkaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ProducerTopic {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaProperties kafkaProperties;


    public ProducerTopic(KafkaTemplate<String, String> kafkaTemplate, KafkaProperties kafkaProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProperties = kafkaProperties;
    }

    public void send(String payload) {

        var topic = kafkaProperties.getTopic().getOrchestrator();

        try {

            log.info("Sending event to topic {} with data: {}", topic, payload);
            kafkaTemplate.send(topic, payload);
        } catch (Exception e) {
            log.error("Error trying to send to topic {} with data: {}", payload, topic);

        }
    }
}
