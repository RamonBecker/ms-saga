package com.order.service.core.usecases.order;

import com.order.service.core.domain.Order;
import com.order.service.core.ports.OrderServiceRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderImpl implements CreateOrder {

    private OrderServiceRepositoryPort repository;
    private static final String TRANSACTION_ID_PATTERN = "%s_%s";

    public Order execute(Order order) {

        order.setCreatedAt(LocalDateTime.now());
        order.setTransactionId(String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID()));

        return repository.save(order);
    }
}
