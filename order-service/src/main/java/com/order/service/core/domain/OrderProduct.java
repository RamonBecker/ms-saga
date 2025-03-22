package com.order.service.core.domain;


import com.order.service.infrastructure.data.db.entities.OrderProductEntity;
import com.order.service.infrastructure.rest.api.responses.OrderProductResponse;
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

    public static OrderProduct fromEntity(OrderProductEntity entity) {
        return OrderProduct.builder()
                .product(Product.fromEntity(entity.getProduct()))
                .quantity(entity.getQuantity())
                .build();
    }

    public static OrderProduct fromResponse(OrderProductResponse response) {
        return OrderProduct.builder()
                .product(Product.fromResponse(response.getProduct()))
                .quantity(response.getQuantity())
                .build();
    }



}
