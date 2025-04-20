package com.example.inventory.service.core.usecases.orderInventory.exception;

public class OrderInventoryExistsException extends RuntimeException {

    public OrderInventoryExistsException(String message) {
        super(message);
    }
}
