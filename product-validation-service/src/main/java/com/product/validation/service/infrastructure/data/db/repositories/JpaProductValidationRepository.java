package com.product.validation.service.infrastructure.data.db.repositories;

import com.product.validation.service.infrastructure.data.db.entities.ProductValidationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface JpaProductValidationRepository extends JpaRepository<ProductValidationEntity, Integer> {

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<ProductValidationEntity> findByOrderIdAndTransactionId(String orderId, String transactionId);
}
