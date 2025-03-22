package com.product.validation.service.core.ports;

import com.product.validation.service.core.domain.ProductValidation;

import java.util.Optional;

public interface ProductValidationRepositoryPort {

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<ProductValidation> findByOrderIdAndTransactionId(String orderId, String transactionId);
}
