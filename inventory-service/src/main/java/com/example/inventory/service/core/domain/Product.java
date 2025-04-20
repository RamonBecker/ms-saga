package com.example.inventory.service.core.domain;


import com.example.inventory.service.infrastructure.dto.ProductDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private String code;
    private double unitValue;

    public static Product fromDomain(ProductDTO dto) {
        return Product.
                builder()
                .code(dto.getCode())
                .unitValue(dto.getUnitValue())
                .build();
    }

}
