package com.order.service.core.domain;

import com.order.service.infrastructure.data.db.entities.OrderEntity;
import com.order.service.infrastructure.data.db.entities.OrderProductEntity;
import com.order.service.infrastructure.rest.api.responses.OrderProductResponse;
import com.order.service.infrastructure.rest.api.responses.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private String id;
    private List<OrderProduct> products;
    private LocalDateTime createdAt;
    private String transactionId;
    private double totalAmount;
    private int totalItems;


    public static Order from(OrderEntity entity) {
        return Order.builder()
                .id(entity.getId())
                .products(Order.toOrdersProducts(entity.getProducts()))
                .createdAt(entity.getCreatedAt())
                .transactionId(entity.getTransactionId())
                .totalAmount(entity.getTotalAmount())
                .totalItems(entity.getTotalItems())
                .build();
    }

    public static Order from(OrderProductResponse response) {
        return Order.builder()
                .products(response.getProducts())
                .build();
    }

    public static Order from(OrderResponse response) {
        return Order.builder()
                .id(response.getId())
                .products(response.getProducts())
                .createdAt(response.getCreatedAt())
                .transactionId(response.getTransactionId())
                .totalAmount(response.getTotalAmount())
                .totalItems(response.getTotalItems())
                .build();
    }

    public static List<OrderProduct> toOrdersProducts(List<OrderProductEntity> products) {

        if (products == null) return new ArrayList<>();

        return products.stream().map(OrderProduct::from).toList();
    }
}
