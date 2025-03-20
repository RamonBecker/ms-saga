package com.order.service.infrastructure.data.db.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.service.core.domain.Event;
import com.order.service.core.domain.Order;
import com.order.service.core.ports.OrderServiceRepositoryPort;
import com.order.service.infrastructure.data.db.converters.OrderConverter;
import com.order.service.infrastructure.data.db.entities.EventEntity;
import com.order.service.infrastructure.data.db.repositories.MongoOrderRepository;
import com.order.service.infrastructure.rest.api.producer.SagaProducer;
import com.order.service.infrastructure.shared.JsonSerializer;

public class OrderService implements OrderServiceRepositoryPort {

    private final MongoOrderRepository mongoOrderRepository;
    private final JsonSerializer jsonSerializer;
    private final SagaProducer producer;
    private final EventService eventService;
    private final OrderConverter orderConverterMapper;

    public OrderService(MongoOrderRepository mongoOrderRepository, JsonSerializer jsonSerializer, SagaProducer producer, EventService eventService, OrderConverter orderConverterMapper) {
        this.mongoOrderRepository = mongoOrderRepository;
        this.jsonSerializer = jsonSerializer;
        this.producer = producer;
        this.eventService = eventService;
        this.orderConverterMapper = orderConverterMapper;
    }

    @Override
    public Order save(Order order) {

        try {
            var orderEntity = orderConverterMapper.orderToOrderEntity(order);

            var savedOrder = mongoOrderRepository.save(orderEntity);

            producer.send(jsonSerializer.toJson(createPayload(order)));
            return orderConverterMapper.orderEntityToOrder(savedOrder);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Event createPayload(Order order) {
        return eventService.save(new EventEntity().fromThis(order));
    }

}
