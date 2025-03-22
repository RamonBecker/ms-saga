package com.order.service.core.domain;

import com.order.service.infrastructure.data.db.entities.OrderEntity;
import com.order.service.infrastructure.data.db.entities.OrderProductEntity;
import com.order.service.infrastructure.rest.api.responses.OrderProductFilterResponse;
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


    public static Order fromEntity(OrderEntity entity) {
        return Order.builder()
                .id(entity.getId())
                .products(Order.toOrdersProductsEntity(entity.getProducts()))
                .createdAt(entity.getCreatedAt())
                .transactionId(entity.getTransactionId())
                .totalAmount(entity.getTotalAmount())
                .totalItems(entity.getTotalItems())
                .build();
    }

    public static Order fromResponse(OrderResponse response) {
        return Order.builder()
                .id(response.getId())
                .products(Order.toOrdersProductsResponse(response.getProducts()))
                .createdAt(response.getCreatedAt())
                .transactionId(response.getTransactionId())
                .totalAmount(response.getTotalAmount())
                .totalItems(response.getTotalItems())
                .build();
    }

    public static Order fromFilterResponse(OrderProductFilterResponse response) {
        return Order.builder()
                .products(Order.toOrdersProductsResponse(response.getProducts()))
                .build();
    }


    public static List<OrderProduct> toOrdersProductsResponse(List<OrderProductResponse> products) {

        if (products == null) return new ArrayList<>();

        return products.stream().map(OrderProduct::fromResponse).toList();
    }

    public static List<OrderProduct> toOrdersProductsEntity(List<OrderProductEntity> products) {

        if (products == null) return new ArrayList<>();

        return products.stream().map(OrderProduct::fromEntity).toList();
    }
}
