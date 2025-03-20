package com.order.service.core.usecases.event.exception;

public class NotFoundEventException extends RuntimeException {

    public NotFoundEventException(String message) {
        super(message);
    }
}
