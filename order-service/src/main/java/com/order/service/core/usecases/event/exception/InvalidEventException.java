package com.order.service.core.usecases.event.exception;

public class InvalidEventException extends RuntimeException {

    public InvalidEventException(String message) {
        super(message);
    }
}
