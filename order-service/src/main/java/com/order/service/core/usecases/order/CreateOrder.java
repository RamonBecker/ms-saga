package com.order.service.core.usecases.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.service.core.domain.Order;
import com.order.service.core.ports.OrderServiceRepositoryPort;

public class CreateOrder {

    private final OrderServiceRepositoryPort repository;

    public CreateOrder(OrderServiceRepositoryPort repository) {
        this.repository = repository;
    }

    public Order execute(Order order) {
        return repository.save(order);
    }
}
