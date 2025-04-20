package com.example.inventory.service.core.usecases.inventory.exception;

public class NotFoundInventoryException extends RuntimeException {

    public NotFoundInventoryException(String message) {
        super(message);
    }
}
