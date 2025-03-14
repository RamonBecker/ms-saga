package com.example.orchestrator.service.infrastructure.config.kafka;


import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.orchestrator.service.infrastructure.constants.Topics.*;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {


    private static final Integer PARTITION_COUNT = 1;
    private static final Integer REPLICA_COUNT = 1;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {

        return new DefaultKafkaConsumerFactory<>(consumerProperties());
    }

    private Map<String, Object> consumerProperties() {

        var defaultProperties = new HashMap<String, Object>();

        defaultProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        defaultProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        defaultProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        defaultProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        defaultProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        return defaultProperties;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {

        return new DefaultKafkaProducerFactory<>(producerProperties());
    }

    private Map<String, Object> producerProperties() {

        var defaultProperties = new HashMap<String, Object>();

        defaultProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        defaultProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        defaultProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); //default_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return defaultProperties;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {

        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic startSagaTopic() {
        return builderTopic(START_SAGA.getTopic());
    }

    @Bean
    public NewTopic orchestratorTopic() {
        return builderTopic(BASE_ORCHESTRATOR.getTopic());
    }

    @Bean
    public NewTopic finishSuccessTopic() {
        return builderTopic(FINISH_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic finishFailTopic() {
        return builderTopic(FINISH_FAIL.getTopic());
    }

    @Bean
    public NewTopic productValidationSuccessTopic() {
        return builderTopic(PRODUCT_VALIDATION_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic productValidationFailTopic() {
        return builderTopic(PRODUCT_VALIDATION_FAIL.getTopic());
    }

    @Bean
    public NewTopic paymentSuccessTopic() {
        return builderTopic(PAYMENT_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic paymentValidationFailTopic() {
        return builderTopic(PAYMENT_FAIL.getTopic());
    }

    @Bean
    public NewTopic inventoryValidationSuccessTopic() {
        return builderTopic(INVENTORY_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic inventoryValidationFailTopic() {
        return builderTopic(INVENTORY_FAIL.getTopic());
    }

    private NewTopic builderTopic(String name) {
        return TopicBuilder.name(name).replicas(REPLICA_COUNT).partitions(PARTITION_COUNT).build();
    }
}
