package com.payment.service.infrastructure.rest.api.consumer.usecases;

import com.payment.service.core.domain.event.Event;
import com.payment.service.infrastructure.shared.constants.Status;

public interface ConsumeEventHandler {
    void handle(Status status, Event event) throws Exception;
}
