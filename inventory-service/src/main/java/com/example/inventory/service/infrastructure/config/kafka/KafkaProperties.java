package com.example.inventory.service.infrastructure.config.kafka;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaProperties {

    private final Consumer consumer = new Consumer();
    private final Topic topic = new Topic();

    @Getter
    private String bootstrapServers;

    public static class Consumer {

        @Getter
        private String groupId;

        @Getter
        private String autoOffsetReset;

    }

    public static class Topic {

        @Getter
        @Setter
        private String orchestrator;

        @Getter
        @Setter
        private String inventorySuccess;

        @Getter
        @Setter
        private String inventoryFail;

    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Topic getTopic() {
        return topic;
    }

}
