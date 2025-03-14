package com.product.validation.service.infrastructure.shared.exception;


import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ValidationException extends ExceptionDetail {

    public ValidationException(final String message) {
        super(BAD_REQUEST.value(), message);
    }
}
