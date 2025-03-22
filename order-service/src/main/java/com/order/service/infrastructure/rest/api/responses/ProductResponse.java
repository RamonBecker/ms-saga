package com.order.service.infrastructure.rest.api.responses;

import com.order.service.core.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String code;
    private double unitValue;

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .code(product.getCode())
                .unitValue(product.getUnitValue())
                .build();
    }
}
