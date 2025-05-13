package com.product.validation.service.core.domain.product;

import com.product.validation.service.infrastructure.data.db.entities.ProductValidationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductValidation {

    private Integer id;
    private String orderId;
    private String transactionId;
    private boolean success;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductValidation fromDomain(ProductValidationEntity entity) {
        return ProductValidation.builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .transactionId(entity.getTransactionId())
                .success(entity.isSuccess())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
