package com.product.validation.service.core.domain;


import com.product.validation.service.infrastructure.dto.order.OrderProductDTO;
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

    public static OrderProduct fromOrderProduct(OrderProductDTO response) {
        return OrderProduct.builder()
                .product(Product.fromProduct(response.getProduct()))
                .quantity(response.getQuantity())
                .build();
    }


}
