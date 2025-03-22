package com.order.service.infrastructure.rest.api.responses;


import com.order.service.core.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {

    private ProductResponse product;
    private int quantity;

    public static OrderProductResponse from(OrderProduct order) {
        return OrderProductResponse.builder()
                .product(ProductResponse.from(order.getProduct()))
                .quantity(order.getQuantity())
                .build();
    }
}
