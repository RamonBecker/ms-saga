package com.order.service.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.order.service.core.ports.OrderServiceRepositoryPort;
import com.order.service.core.usecases.event.GetEventImpl;
import com.order.service.core.usecases.order.CreateOrderImpl;
import com.order.service.infrastructure.config.kafka.KafkaProperties;
import com.order.service.infrastructure.data.db.repositories.MongoEventRepository;
import com.order.service.infrastructure.data.db.repositories.MongoOrderRepository;
import com.order.service.infrastructure.data.db.repositories.impl.EventRepository;
import com.order.service.infrastructure.data.db.repositories.impl.OrderRepository;
import com.order.service.infrastructure.rest.api.consumer.EventConsumer;
import com.order.service.infrastructure.rest.api.producer.SagaProducer;
import com.order.service.infrastructure.rest.api.serializers.JsonSerializerImpl;
import com.order.service.infrastructure.shared.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class Module {


    @Autowired
    private MongoEventRepository eventRepository;

    @Autowired
    private MongoOrderRepository orderRepository;

    @Autowired
    private KafkaProperties kafkaProperties;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Bean
    public ObjectMapper objectMapper() {

        var objMapper = new ObjectMapper();

        objMapper.registerModule(new JavaTimeModule());

        return objMapper;
    }

    @Bean
    public JsonSerializer createJsonSerializer() {
        return new JsonSerializerImpl(objectMapper());
    }

    @Bean
    public SagaProducer createSagaProducer() {
        return new SagaProducer(kafkaTemplate, kafkaProperties);
    }

    @Bean
    public OrderRepository createOrderRepository() {
        return new OrderRepository(orderRepository, createJsonSerializer(), createSagaProducer(), createEventRepository());
    }


    @Bean
    public EventRepository createEventRepository() {
        return new EventRepository(eventRepository);
    }


    @Bean
    public GetEventImpl createGetEvent() {
        return new GetEventImpl(createEventRepository());
    }

    @Bean
    public CreateOrderImpl createOrder() {
        return new CreateOrderImpl(createOrderRepository());
    }

    @Bean
    public EventConsumer createEventConsumer() {
        return new EventConsumer(createJsonSerializer(), createEventRepository());
    }
}
