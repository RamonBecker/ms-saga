package com.order.service.infrastructure.data.db.entities;


import com.order.service.core.domain.OrderProduct;
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


    public static OrderProductEntity from(OrderProduct product) {
        return OrderProductEntity.builder()
                 .product(ProductEntity.from(product.getProduct()))
                .quantity(product.getQuantity()).build();

    }

}
