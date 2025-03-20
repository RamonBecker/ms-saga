package com.order.service.infrastructure.rest.api.converters;

import com.order.service.infrastructure.data.db.entities.OrderEntity;
import com.order.service.infrastructure.rest.api.responses.OrderResponse;
import com.order.service.infrastructure.shared.RestConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderRestConverter implements RestConverter<OrderResponse, OrderEntity> {

    private static final String TRANSACTION_ID_PATTERN = "%s_%s";

    @Override
    public OrderEntity mapToEntity(OrderResponse rest) {
        return OrderEntity.builder()
                .products(rest.getProducts())
                .createdAt(LocalDateTime.now())
                .transactionId(String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID())).build();
    }

    @Override
    public OrderResponse mapToRest(OrderEntity entity) {
        return OrderResponse.builder()
                        .
                id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .transactionId(entity.getTransactionId())
                .products(entity.getProducts()).build();
    }
}
