package com.payment.service.infrastructure.rest.api.consumer.impl;

import com.payment.service.core.domain.event.Event;
import com.payment.service.infrastructure.rest.api.consumer.usecases.ConsumeEventHandler;
import com.payment.service.infrastructure.rest.api.payment.PaymentResolver;
import com.payment.service.infrastructure.shared.constants.Status;

public class ConsumeEventHandlerImpl implements ConsumeEventHandler {

    private final PaymentResolver resolver;

    public ConsumeEventHandlerImpl(PaymentResolver resolver) {
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
