package com.order.service.infrastructure.data.db.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.service.core.domain.event.Event;
import com.order.service.core.domain.order.Order;
import com.order.service.core.ports.OrderServiceRepositoryPort;
import com.order.service.infrastructure.data.db.entities.OrderEntity;
import com.order.service.infrastructure.data.db.repositories.MongoOrderRepository;
import com.order.service.infrastructure.rest.api.producer.ProducerTopic;
import com.order.service.infrastructure.rest.api.serializers.JsonSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class OrderRepository implements OrderServiceRepositoryPort {

    private MongoOrderRepository repository;
    private JsonSerializer jsonSerializer;
    private ProducerTopic producer;
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
