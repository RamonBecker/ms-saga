package com.product.validation.service.core.usecases.validation.impl;

import com.product.validation.service.core.ports.ProductValidationRepositoryPort;
import com.product.validation.service.core.usecases.validation.GetExistsProductValidationByOrderAndTransaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class GetExistsProductValidationByOrderAndTransactionImpl implements GetExistsProductValidationByOrderAndTransaction {

    private ProductValidationRepositoryPort repository;

    @Override
    public Boolean execute(String orderId, String transactionId) {
        return repository.existsByOrderIdAndTransactionId(orderId, transactionId);
    }
}
