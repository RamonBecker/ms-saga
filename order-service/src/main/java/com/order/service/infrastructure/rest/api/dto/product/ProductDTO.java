package com.order.service.infrastructure.rest.api.dto.product;

import com.order.service.core.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String code;
    private double unitValue;

    public static ProductDTO from(Product product) {
        return ProductDTO.builder()
                .code(product.getCode())
                .unitValue(product.getUnitValue())
                .build();
    }
}
