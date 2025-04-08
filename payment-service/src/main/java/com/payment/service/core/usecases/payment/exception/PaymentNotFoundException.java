package com.payment.service.core.usecases.payment.exception;

import com.payment.service.infrastructure.shared.exception.ExceptionDetail;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class PaymentNotFoundException extends ExceptionDetail {
    public PaymentNotFoundException(String message) {
        super(NOT_FOUND.value(), message);
    }
}
