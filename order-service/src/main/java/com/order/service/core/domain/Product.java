package com.order.service.core.domain;


import com.order.service.infrastructure.data.db.entities.ProductEntity;
import com.order.service.infrastructure.rest.api.responses.ProductResponse;
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

    public static Product fromEntity(ProductEntity entity) {
        return Product.builder()
                .code(entity.getCode())
                .unitValue(entity.getUnitValue())
                .build();
    }

    public static Product fromResponse(ProductResponse response) {
        return Product.builder()
                .code(response.getCode())
                .unitValue(response.getUnitValue())
                .build();
    }
}
