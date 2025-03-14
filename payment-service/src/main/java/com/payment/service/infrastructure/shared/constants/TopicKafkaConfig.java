package com.payment.service.infrastructure.shared.constants;

import org.springframework.beans.factory.annotation.Value;

public final class TopicKafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    public static String BOOTSTRAP_SERVERS;

    @Value("${spring.kafka.consumer.group-id}")
    public static String GROUP_ID;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    public static String AUTO_OFFSET_RESET;

    @Value("${spring.kafka.topic.orchestrator}")
    public static String ORCHESTRATOR;

    @Value("${spring.kafka.topic.payment-success}")
    public static String PAYMENT_SUCCESS;

    @Value("${spring.kafka.topic.payment-fail}")
    public static String PAYMENT_FAIL;

}