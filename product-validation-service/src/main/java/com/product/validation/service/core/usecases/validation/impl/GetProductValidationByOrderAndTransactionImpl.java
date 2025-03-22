package com.product.validation.service.core.usecases.validation.impl;

import com.product.validation.service.core.domain.ProductValidation;
import com.product.validation.service.core.ports.ProductValidationRepositoryPort;
import com.product.validation.service.core.usecases.validation.GetProductValidationByOrderAndTransaction;

import java.util.Optional;

public class GetProductValidationByOrderAndTransactionImpl implements GetProductValidationByOrderAndTransaction {

    private ProductValidationRepositoryPort repository;

    @Override
    public Optional<ProductValidation> execute(String orderId, String transactionId) {
        return repository.findByOrderIdAndTransactionId(orderId, transactionId);
    }
}
