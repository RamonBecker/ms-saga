package com.order.service.infrastructure.data.db.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.service.core.domain.Event;
import com.order.service.core.domain.Order;
import com.order.service.core.ports.OrderServiceRepositoryPort;
import com.order.service.infrastructure.data.db.entities.EventEntity;
import com.order.service.infrastructure.data.db.entities.OrderEntity;
import com.order.service.infrastructure.data.db.repositories.MongoOrderRepository;
import com.order.service.infrastructure.rest.api.producer.SagaProducer;
import com.order.service.infrastructure.shared.JsonSerializer;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository implements OrderServiceRepositoryPort {

    private MongoOrderRepository repository;
    private JsonSerializer jsonSerializer;
    private SagaProducer producer;
    private EventRepository service;

    public OrderRepository() {
    }

    public OrderRepository(MongoOrderRepository mongoOrderRepository, JsonSerializer jsonSerializer, SagaProducer producer, EventRepository eventService) {
        this.repository = mongoOrderRepository;
        this.jsonSerializer = jsonSerializer;
        this.producer = producer;
        this.service = eventService;
    }

    @Override
    public Order save(Order order) {

        try {

            var orderEntity = OrderEntity.from(order);

            var savedOrder = repository.save(orderEntity);

            producer.send(jsonSerializer.toJson(createPayload(order)));

            return Order.from(savedOrder);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Event createPayload(Order order) {
        return service.save(new EventEntity().setOrder(order));
    }

}
