package com.product.validation.service.infrastructure.rest.api.consumer.usecases;

import com.product.validation.service.core.domain.event.Event;
import com.product.validation.service.infrastructure.shared.constants.Status;

public interface ConsumeEventHandler {

    void handle(Status status, Event event) throws Exception;

}
