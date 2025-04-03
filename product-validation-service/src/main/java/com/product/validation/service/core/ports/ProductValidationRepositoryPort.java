package com.product.validation.service.core.ports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.product.validation.service.core.domain.Event;
import com.product.validation.service.core.domain.ProductValidation;

import java.util.Optional;

public interface ProductValidationRepositoryPort {

    void send(Event event) throws Exception;

    void save(ProductValidation productValidation);

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

    Optional<ProductValidation> findByOrderIdAndTransactionId(String orderId, String transactionId);
}
