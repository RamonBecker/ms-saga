package com.order.service.core.usecases.event.impl;

import com.order.service.core.domain.event.Event;
import com.order.service.core.ports.EventRepositoryPort;
import com.order.service.core.usecases.event.GetAllEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class GetAllEventImpl implements GetAllEvent {

    private EventRepositoryPort repository;


    public List<Event> execute() {
        return repository.getAll();
    }
}
