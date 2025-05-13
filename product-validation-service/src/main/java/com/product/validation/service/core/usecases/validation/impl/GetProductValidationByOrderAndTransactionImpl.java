package com.product.validation.service.core.usecases.validation.impl;

import com.product.validation.service.core.domain.product.ProductValidation;
import com.product.validation.service.core.ports.ProductValidationRepositoryPort;
import com.product.validation.service.core.usecases.validation.GetProductValidationByOrderAndTransaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class GetProductValidationByOrderAndTransactionImpl implements GetProductValidationByOrderAndTransaction {

    private ProductValidationRepositoryPort repository;

    @Override
    public Optional<ProductValidation> execute(String orderId, String transactionId) {
        return repository.findByOrderIdAndTransactionId(orderId, transactionId);
    }
}
