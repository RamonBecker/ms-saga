package com.order.service.core.usecases.event.impl;

import com.order.service.core.domain.Event;
import com.order.service.core.ports.EventRepositoryPort;
import com.order.service.core.usecases.event.CreateEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CreateEventImpl implements CreateEvent {


    private EventRepositoryPort repository;

    public Event execute(Event event) {
        return repository.save(event);
    }
}
