package com.example.inventory.service.infrastructure.rest.api.consumer.usecases;

import com.example.inventory.service.core.domain.event.Event;
import com.example.inventory.service.infrastructure.shared.constants.Status;

public interface ConsumeEventHandler {
    void handle(Status status, Event event) throws Exception;
}
