package com.payment.service.core.domain.order;


import com.payment.service.core.domain.product.Product;
import com.payment.service.infrastructure.dto.order.OrderProductDTO;
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


    public static OrderProduct fromDomain(OrderProductDTO response) {
        return OrderProduct.builder()
                .product(Product.fromDomain(response.getProduct()))
                .quantity(response.getQuantity())
                .build();
    }
}
