package com.product.validation.service.infrastructure.data.db.entities;

import com.product.validation.service.core.domain.product.ProductValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_validation")
@Builder
public class ProductValidationEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private boolean success;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = getDateTimeNow();
        updatedAt = getDateTimeNow();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = getDateTimeNow();
    }

    private LocalDateTime getDateTimeNow() {
        return LocalDateTime.now();
    }

    public static ProductValidationEntity fromEntity(ProductValidation product) {
        return ProductValidationEntity.builder()
                .id(product.getId())
                .orderId(product.getOrderId())
                .transactionId(product.getTransactionId())
                .success(product.isSuccess())
                .build();
    }
}
