package com.order.service.infrastructure.shared.constants;

import org.springframework.beans.factory.annotation.Value;

public final class TopicKafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    public static String BOOTSTRAP_SERVERS;

    @Value("${spring.kafka.consumer.group-id}")
    public static  String GROUP_ID;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    public static  String AUTO_OFFSET_RESET;

    @Value("${spring.kafka.topic.start-saga}")
    public static  String START_SAGA;

    @Value("${spring.kafka.topic.notify-ending}")
    public static  String NOTIFY_ENDING;

}
