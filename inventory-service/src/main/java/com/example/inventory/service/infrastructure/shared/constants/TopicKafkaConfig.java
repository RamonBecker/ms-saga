package com.example.inventory.service.infrastructure.shared.constants;

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

    @Value("${spring.kafka.topic.inventory-success}")
    public static String INVENTORY_SUCCESS;

    @Value("${spring.kafka.topic.inventory-fail}")
    public static String INVENTORY_FAIL;
}