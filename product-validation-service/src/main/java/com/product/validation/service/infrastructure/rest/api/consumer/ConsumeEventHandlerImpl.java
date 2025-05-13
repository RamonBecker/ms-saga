package com.product.validation.service.infrastructure.rest.api.consumer;

import com.product.validation.service.core.domain.event.Event;
import com.product.validation.service.infrastructure.rest.api.consumer.usecases.ConsumeEventHandler;
import com.product.validation.service.infrastructure.rest.api.product.ProductResolver;
import com.product.validation.service.infrastructure.shared.constants.Status;

public class ConsumeEventHandlerImpl implements ConsumeEventHandler {

    private final ProductResolver resolver;

    public ConsumeEventHandlerImpl(ProductResolver resolver) {
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
