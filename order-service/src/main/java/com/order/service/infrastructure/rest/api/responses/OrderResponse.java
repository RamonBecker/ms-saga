package com.order.service.infrastructure.rest.api.responses;

import com.order.service.core.domain.Order;
import com.order.service.core.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private String id;
    private LocalDateTime createdAt;
    private String transactionId;
    private double totalAmount;
    private int totalItems;
    private List<OrderProduct> products;

    public static OrderResponse from(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .transactionId(order.getTransactionId())
                .totalAmount(order.getTotalAmount())
                .totalItems(order.getTotalItems())
                .products(order.getProducts())
                .build();
    }
}
