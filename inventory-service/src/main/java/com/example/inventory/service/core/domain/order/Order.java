package com.example.inventory.service.core.domain.order;

import com.example.inventory.service.infrastructure.dto.OrderDTO;
import com.example.inventory.service.infrastructure.dto.OrderProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;
    private List<OrderProduct> products;
    private LocalDateTime createdAt;
    private String transactionId;
    private double totalAmount;
    private int totalItems;

    public static Order fromDomain(OrderDTO dto) {
        return Order.builder()
                .id(dto.getId())
                .products(Order.toOrdersProducts(dto.getProducts()))
                .createdAt(dto.getCreatedAt())
                .transactionId(dto.getTransactionId())
                .totalAmount(dto.getTotalAmount())
                .totalItems(dto.getTotalItems())
                .build();
    }


    public static List<OrderProduct> toOrdersProducts(List<OrderProductDTO> products) {

        if (products == null) return new ArrayList<>();

        return products.stream().map(OrderProduct::fromOrderProduct).toList();
    }

}
