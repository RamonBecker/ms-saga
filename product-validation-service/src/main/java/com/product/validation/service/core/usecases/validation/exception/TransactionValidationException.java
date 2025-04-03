package com.product.validation.service.core.usecases.validation.exception;

public class TransactionValidationException extends RuntimeException {

    public TransactionValidationException(String message) {
        super(message);
    }
}
