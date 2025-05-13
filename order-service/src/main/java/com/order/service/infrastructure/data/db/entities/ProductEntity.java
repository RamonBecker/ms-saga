package com.order.service.infrastructure.data.db.entities;


import com.order.service.core.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    private String code;
    private double unitValue;

    public static ProductEntity fromEntity(Product product) {
        return ProductEntity.builder().code(product.getCode()).unitValue(product.getUnitValue()).build();
    }
}
