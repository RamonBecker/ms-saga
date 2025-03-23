package com.order.service.infrastructure.rest.api.dto.order;


import com.order.service.core.domain.OrderProduct;
import com.order.service.infrastructure.rest.api.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO {

    private ProductDTO product;
    private int quantity;

    public static OrderProductDTO from(OrderProduct order) {
        return OrderProductDTO.builder()
                .product(ProductDTO.from(order.getProduct()))
                .quantity(order.getQuantity())
                .build();
    }
}
