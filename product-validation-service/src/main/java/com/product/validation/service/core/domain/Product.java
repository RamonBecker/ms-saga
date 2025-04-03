package com.product.validation.service.core.domain;


import com.product.validation.service.infrastructure.dto.product.ProductDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private String code;
    private double unitValue;

    public static Product fromProduct(ProductDTO dto) {
        return Product.
                builder()
                .code(dto.getCode())
                .unitValue(dto.getUnitValue())
                .build();
    }
}
