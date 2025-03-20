package com.order.service.infrastructure.data.db.entities;

import com.order.service.core.domain.Order;
import com.order.service.core.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<OrderProductEntity> toOrdersProductsEntities(List<OrderProduct> products) {
        return products.stream().map(OrderProductEntity::from).toList();
    }

}
