package com.order.service.infrastructure.rest.api.producer;



import com.order.service.infrastructure.config.kafka.KafkaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SagaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaProperties kafkaProperties;


    public SagaProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaProperties kafkaProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProperties = kafkaProperties;
    }


    public void send(String payload) {
        var topicProductValidationStart = kafkaProperties.getTopic().getProductValidationStart();
        try {
            log.info("Sending event to topic {} with data: {}", topicProductValidationStart, payload);
            kafkaTemplate.send(topicProductValidationStart, payload);
        } catch (Exception e) {
            log.error("Error trying to send to topic {} with data: {}", payload, topicProductValidationStart);
        }
    }
}
