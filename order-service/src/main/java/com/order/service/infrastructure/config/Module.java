package com.order.service.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.order.service.core.usecases.event.impl.GetAllEventImpl;
import com.order.service.core.usecases.event.impl.GetEventByOrderImpl;
import com.order.service.core.usecases.event.impl.GetEventByTransactionImpl;
import com.order.service.core.usecases.notify.NotifyEnd;
import com.order.service.core.usecases.notify.impl.NotifyEndImpl;
import com.order.service.core.usecases.order.impl.CreateOrderImpl;
import com.order.service.infrastructure.config.kafka.KafkaProperties;
import com.order.service.infrastructure.data.db.repositories.MongoEventRepository;
import com.order.service.infrastructure.data.db.repositories.MongoOrderRepository;
import com.order.service.infrastructure.data.db.repositories.impl.EventRepository;
import com.order.service.infrastructure.data.db.repositories.impl.OrderRepository;
import com.order.service.infrastructure.rest.api.consumer.ConsumerTopic;
import com.order.service.infrastructure.rest.api.producer.ProducerTopic;
import com.order.service.infrastructure.rest.api.serializers.impl.JsonSerializerImpl;
import com.order.service.infrastructure.rest.api.serializers.JsonSerializer;
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
    public ProducerTopic createSagaProducer() {
        return new ProducerTopic(kafkaTemplate, kafkaProperties);
    }

    @Bean
    public OrderRepository createOrderRepository() {
        return new OrderRepository(orderRepository, createJsonSerializer(), createSagaProducer(), createEventRepository());
    }

    @Bean
    public NotifyEnd createNotifyEnd() {
        return new NotifyEndImpl(createEventRepository());
    }

    @Bean
    public EventRepository createEventRepository() {
        return new EventRepository(eventRepository);
    }

    @Bean
    public GetAllEventImpl createGetAllEvent() {
        return new GetAllEventImpl(createEventRepository());
    }

    @Bean
    public GetEventByOrderImpl createGetEventByOrderId() {
        return new GetEventByOrderImpl(createEventRepository());
    }

    @Bean
    public GetEventByTransactionImpl createGetEventByTransactionId() {
        return new GetEventByTransactionImpl(createEventRepository());
    }

    @Bean
    public CreateOrderImpl createAllOrder() {
        return new CreateOrderImpl(createOrderRepository());
    }

    @Bean
    public ConsumerTopic createEventConsumer() {
        return new ConsumerTopic(createJsonSerializer(), createNotifyEnd());
    }
}
