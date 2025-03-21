package com.order.service.core.usecases.event;

import com.order.service.core.domain.Event;
import com.order.service.core.ports.EventRepositoryPort;

public class CreateEventImpl implements CreateEvent{


    private EventRepositoryPort repository;

    public CreateEventImpl() {
    }

    public CreateEventImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    public Event create(Event event) {
        return repository.save(event);
    }
}
