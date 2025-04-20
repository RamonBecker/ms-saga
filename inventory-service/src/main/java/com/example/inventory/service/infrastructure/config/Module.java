package com.example.inventory.service.infrastructure.config;

import com.example.inventory.service.core.ports.InventoryRepositoryPort;
import com.example.inventory.service.core.ports.OrderInventoryRepositoryPort;
import com.example.inventory.service.core.usecases.inventory.SaveInventory;
import com.example.inventory.service.core.usecases.inventory.impl.SaveInventoryImpl;
import com.example.inventory.service.core.usecases.orderInventory.RollbackOrderInventory;
import com.example.inventory.service.core.usecases.orderInventory.SaveOrderInventory;
import com.example.inventory.service.core.usecases.orderInventory.impl.RollbackOrderInventoryImpl;
import com.example.inventory.service.core.usecases.orderInventory.impl.SaveOrderInventoryImpl;
import com.example.inventory.service.infrastructure.config.kafka.KafkaProperties;
import com.example.inventory.service.infrastructure.data.db.repositories.JpaInventoryRepository;
import com.example.inventory.service.infrastructure.data.db.repositories.JpaOrderInventoryRepository;
import com.example.inventory.service.infrastructure.data.db.repositories.impl.InventoryRepository;
import com.example.inventory.service.infrastructure.data.db.repositories.impl.OrderInventoryRepository;
import com.example.inventory.service.infrastructure.rest.api.inventory.SendInventory;
import com.example.inventory.service.infrastructure.rest.api.producer.ProducerTopic;
import com.example.inventory.service.infrastructure.serializers.JsonSerializer;
import com.example.inventory.service.infrastructure.serializers.impl.JsonSerializerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class Module {

    @Autowired
    private JpaInventoryRepository inventoryRepository;
    @Autowired
    private JpaOrderInventoryRepository orderInventoryRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ObjectMapper createObjectMapper() {

        var objMapper = new ObjectMapper();

        objMapper.registerModule(new JavaTimeModule());

        return objMapper;
    }

    @Bean
    public JsonSerializer createJsonSerializer() {
        return new JsonSerializerImpl(createObjectMapper());
    }

    @Bean
    public InventoryRepository createInventoryRepository() {
        return new InventoryRepository(inventoryRepository);
    }

    @Bean
    public OrderInventoryRepository createOrderInventoryRepository() {
        return new OrderInventoryRepository(orderInventoryRepository);
    }

    @Bean
    public ProducerTopic createProducerTopic() {
        return new ProducerTopic(kafkaTemplate, kafkaProperties);
    }

    @Bean
    public SendInventory createSendInventory() {
        return new SendInventory(
                createSaveOrderInventory(),
                createSaveInventory(),
                createRollbackOrderInventory(),
                createProducerTopic(),
                createJsonSerializer()
        );
    }

    @Bean
    public SaveInventory createSaveInventory() {
        return new SaveInventoryImpl(createInventoryRepository());
    }

    @Bean
    public RollbackOrderInventory createRollbackOrderInventory() {
        return new RollbackOrderInventoryImpl(createOrderInventoryRepository(), createInventoryRepository());
    }

    @Bean
    public SaveOrderInventory createSaveOrderInventory() {
        return new SaveOrderInventoryImpl(createOrderInventoryRepository(), createInventoryRepository());
    }

}
