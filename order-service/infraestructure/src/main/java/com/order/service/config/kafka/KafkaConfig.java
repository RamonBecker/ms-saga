package com.order.service.config.kafka;


import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;


    @Bean
    public ConsumerFactory<String, String> consumerFactory(){

        return new DefaultKafkaConsumerFactory<>(consumerProperties());
    }

    private Map<String, Object> consumerProperties(){

        var defaultProperties = new HashMap<String, Object>();

        defaultProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        defaultProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        defaultProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        defaultProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        defaultProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        return defaultProperties;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory(){

        return new DefaultKafkaProducerFactory<>(producerProperties());
    }

    private Map<String, Object> producerProperties(){

        var defaultProperties = new HashMap<String, Object>();

        defaultProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        defaultProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        defaultProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); //default_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return defaultProperties;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory){
        
        return new KafkaTemplate<>(producerFactory);
    }
}
