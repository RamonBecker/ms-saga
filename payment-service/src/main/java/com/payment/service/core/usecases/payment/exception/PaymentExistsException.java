package com.payment.service.core.usecases.payment.exception;


import com.payment.service.infrastructure.shared.exception.ExceptionDetail;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class PaymentExistsException extends ExceptionDetail {
    public PaymentExistsException(String message) {
        super(BAD_REQUEST.value(), message);
    }
}
