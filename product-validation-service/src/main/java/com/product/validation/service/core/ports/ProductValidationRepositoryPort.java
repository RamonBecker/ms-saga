package com.product.validation.service.core.ports;

import com.product.validation.service.core.domain.event.Event;
import com.product.validation.service.core.domain.product.ProductValidation;

import java.util.Optional;

public interface ProductValidationRepositoryPort {

    void send(Event event) throws Exception;

    void save(ProductValidation productValidation);

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<ProductValidation> findByOrderIdAndTransactionId(String orderId, String transactionId);
}
