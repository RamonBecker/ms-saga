package com.order.service.core.usecases.notify.impl;

import com.order.service.core.domain.event.Event;
import com.order.service.core.ports.EventRepositoryPort;
import com.order.service.core.usecases.notify.NotifyEnd;

import java.time.LocalDateTime;

public class NotifyEndImpl implements NotifyEnd {

    private final EventRepositoryPort repository;

    public NotifyEndImpl(EventRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Event event) {
        event.setOrderId(event.getOrder().getId());
        event.setCreatedAt(LocalDateTime.now());
        repository.save(event);
    }
}
