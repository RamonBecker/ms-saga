package com.order.service.infrastructure.data.db.entities;

import com.order.service.core.domain.order.Order;
import com.order.service.core.domain.order.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class OrderEntity implements Serializable {

    @Id
    private String id;
    private List<OrderProductEntity> products;
    private LocalDateTime createdAt;
    private String transactionId;
    private double totalAmount;
    private int totalItems;


    public static OrderEntity fromEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .products(OrderEntity.toOrdersProductsEntities(order.getProducts()))
                .createdAt(order.getCreatedAt())
                .transactionId(order.getTransactionId())
                .totalAmount(order.getTotalAmount())
                .totalItems(order.getTotalItems())
                .build();
    }

    public static List<OrderProductEntity> toOrdersProductsEntities(List<OrderProduct> products) {

        if (products == null) return new ArrayList<>();

        return products.stream().map(OrderProductEntity::fromEntity).toList();
    }

}
