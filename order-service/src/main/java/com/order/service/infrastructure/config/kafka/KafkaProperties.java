package com.order.service.infrastructure.config.kafka;


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
    @Setter
    private String bootstrapServers;

    public static class Consumer {

        @Getter
        @Setter

        private String groupId;

        @Getter
        @Setter
        private String autoOffsetReset;

    }

    public static class Topic {

        @Getter
        @Setter
        private String startSaga;

        @Getter
        @Setter

        private String notifyEnding;

    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Topic getTopic() {
        return topic;
    }

}
