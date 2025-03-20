package com.order.service.core.usecases.event;

import com.order.service.core.domain.Event;
import com.order.service.core.ports.EventServiceRepositoryPort;

public class CreateEvent {

    private final EventServiceRepositoryPort repository;

    public CreateEvent(EventServiceRepositoryPort repository) {
        this.repository = repository;
    }

    public Event execute(Event event) {
        return repository.save(event);
    }
}
