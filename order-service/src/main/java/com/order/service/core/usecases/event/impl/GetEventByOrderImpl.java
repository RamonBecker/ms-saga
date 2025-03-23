package com.order.service.core.usecases.event.impl;

import com.order.service.core.domain.Event;
import com.order.service.core.ports.EventRepositoryPort;
import com.order.service.core.usecases.event.GetEventByOrder;
import com.order.service.core.usecases.event.exception.InvalidEventException;
import com.order.service.core.usecases.event.exception.NotFoundEventException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class GetEventByOrderImpl implements GetEventByOrder {

    private EventRepositoryPort repository;

    @Override
    public Optional<Event> execute(String orderId) {
        if (orderId == null)
            throw new InvalidEventException("Order id cannot be null");

        return Optional.ofNullable(repository.findByOrderId(orderId).orElseThrow(() -> new NotFoundEventException("Event not found by order id")));
    }
}

