package com.example.inventory.service.infrastructure.rest.api.consumer;

import com.example.inventory.service.core.domain.event.Event;
import com.example.inventory.service.infrastructure.rest.api.consumer.usecases.ConsumeEventHandler;
import com.example.inventory.service.infrastructure.rest.api.inventory.InventoryResolver;
import com.example.inventory.service.infrastructure.shared.constants.Status;

public class ConsumeEventHandlerImpl implements ConsumeEventHandler {

    private final InventoryResolver resolver;

    public ConsumeEventHandlerImpl(InventoryResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void handle(Status status, Event event) throws Exception {

        switch (status) {
            case SUCCESS:
                resolver.success(event);
                break;
            case FAIL:
                resolver.rollback(event);
                break;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
    }
}
