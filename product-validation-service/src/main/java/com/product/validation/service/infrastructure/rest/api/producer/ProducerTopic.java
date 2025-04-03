package com.product.validation.service.infrastructure.rest.api.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.product.validation.service.infrastructure.config.kafka.KafkaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

// Criar um componente do spring para criar os eventos
// Produtor e Consumidor devem ficar separados com suas regras


@Slf4j
@Component
public class ProducerTopic {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public ProducerTopic(KafkaTemplate<String, String> kafkaTemplate, KafkaProperties kafkaProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProperties = kafkaProperties;
    }

    public void send(String payload) throws JsonProcessingException {

        var topic = kafkaProperties.getTopic().getOrchestrator();

        try {
            log.info("Sending event to topic {} with data: {}", topic, payload);
            kafkaTemplate.send(topic, payload);
        } catch (Exception e) {
            log.error("Error trying to send to topic {} with data: {}", payload, topic);

        }
    }
}
