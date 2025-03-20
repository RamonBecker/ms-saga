package com.order.service.core.domain;


import com.order.service.infrastructure.data.db.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private String code;
    private double unitValue;


    public static Product from(ProductEntity entity) {
        return Product.builder()
                .code(entity.getCode())
                .unitValue(entity.getUnitValue())
                .build();
    }
}
