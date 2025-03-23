package com.order.service.infrastructure.rest.api.dto.order;

import com.order.service.core.domain.Order;
import com.order.service.core.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private String id;
    private LocalDateTime createdAt;
    private String transactionId;
    private double totalAmount;
    private int totalItems;
    private List<OrderProductDTO> products;

    public static OrderDTO from(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .transactionId(order.getTransactionId())
                .totalAmount(order.getTotalAmount())
                .totalItems(order.getTotalItems())
                .products(OrderDTO.toOrdersProductsResponse(order.getProducts()))
                .build();
    }

    public static List<OrderProductDTO> toOrdersProductsResponse(List<OrderProduct> products) {

        if (products == null) return new ArrayList<>();

        return products.stream().map(OrderProductDTO::from).toList();
    }
}
