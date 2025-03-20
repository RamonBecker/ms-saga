package com.order.service.core.domain;


import com.order.service.infrastructure.data.db.entities.OrderProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProduct {

    private Product product;
    private int quantity;

    public static OrderProduct from(OrderProductEntity entity) {
        return OrderProduct.builder()
                .product(Product.from(entity.getProduct()))
                .quantity(entity.getQuantity())
                .build();
    }



}
