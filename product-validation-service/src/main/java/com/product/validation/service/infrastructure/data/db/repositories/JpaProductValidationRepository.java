package com.product.validation.service.infrastructure.data.db.repositories;

import com.product.validation.service.infrastructure.data.db.entities.ProductValidationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProductValidationRepository extends JpaRepository<ProductValidationEntity, Integer> {

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<ProductValidationEntity> findByOrderIdAndTransactionId(String orderId, String transactionId);
}
