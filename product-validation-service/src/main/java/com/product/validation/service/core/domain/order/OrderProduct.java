package com.product.validation.service.core.domain.order;


import com.product.validation.service.core.domain.product.Product;
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
                .product(Product.fromDomain(response.getProduct()))
                .quantity(response.getQuantity())
                .build();
    }


}
