package com.order.service.infrastructure.data.db.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.service.core.domain.Event;
import com.order.service.core.domain.Order;
import com.order.service.core.ports.OrderServiceRepositoryPort;
import com.order.service.infrastructure.data.db.entities.OrderEntity;
import com.order.service.infrastructure.data.db.repositories.MongoOrderRepository;
import com.order.service.infrastructure.rest.api.producer.SagaProducer;
import com.order.service.infrastructure.shared.JsonSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class OrderRepository implements OrderServiceRepositoryPort {

    private MongoOrderRepository repository;
    private JsonSerializer jsonSerializer;
    private SagaProducer producer;
    private EventRepository service;

    @Override
    public Order save(Order order) {

        try {

            var orderEntity = OrderEntity.fromEntity(order);

            var savedOrder = repository.save(orderEntity);
            
            producer.send(jsonSerializer.toJson(createPayload(Order.fromEntity(savedOrder))));

            return Order.fromEntity(savedOrder);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Event createPayload(Order order) {
        return service.save(new Event().setOrder(order));
    }

}
