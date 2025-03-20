package com.order.service.core.domain;

import com.order.service.infrastructure.data.db.entities.OrderProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    public static List<OrderProduct> toOrdersProducts(List<OrderProductEntity> products) {
        return products.stream().map(OrderProduct::from).toList();
    }
}
