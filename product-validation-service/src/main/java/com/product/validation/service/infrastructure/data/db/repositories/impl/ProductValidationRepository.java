package com.product.validation.service.infrastructure.data.db.repositories.impl;

import com.product.validation.service.core.domain.ProductValidation;
import com.product.validation.service.core.ports.ProductValidationRepositoryPort;
import com.product.validation.service.infrastructure.data.db.repositories.JpaProductValidationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class ProductValidationRepository implements ProductValidationRepositoryPort {

    private JpaProductValidationRepository repository;

    @Override
    public Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId) {
        return repository.existsByOrderIdAndTransactionId(orderId, transactionId);
    }

    @Override
    public Optional<ProductValidation> findByOrderIdAndTransactionId(String orderId, String transactionId) {
        return repository.findByOrderIdAndTransactionId(orderId, transactionId).map(ProductValidation::fromEntity);
    }
}
