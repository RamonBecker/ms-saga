package com.order.service.infrastructure.data.db.entities;


import com.order.service.core.domain.order.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductEntity {

    private ProductEntity product;
    private int quantity;


    public static OrderProductEntity fromEntity(OrderProduct product) {
        return OrderProductEntity.builder()
                 .product(ProductEntity.fromEntity(product.getProduct()))
                .quantity(product.getQuantity()).build();

    }

}
