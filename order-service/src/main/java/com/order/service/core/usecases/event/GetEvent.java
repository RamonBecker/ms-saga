package com.order.service.core.usecases.event;

import com.order.service.core.domain.Event;
import com.order.service.core.ports.EventServiceRepositoryPort;
import com.order.service.core.usecases.event.exception.InvalidEventException;
import com.order.service.core.usecases.event.exception.NotFoundEventException;

import java.util.List;
import java.util.Optional;

public class GetEvent {

    private final EventServiceRepositoryPort repository;

    public GetEvent(EventServiceRepositoryPort eventServiceRepositoryPort) {
        this.repository = eventServiceRepositoryPort;
    }

    public List<Event> getAll() {
        return repository.getAll();
    }

    public Optional<Event> getByTransactionId(String transactionId) {

        if (transactionId == null)
            throw new InvalidEventException("Transaction id cannot be null");

        return Optional.ofNullable(repository.findByTransactionId(transactionId).orElseThrow(() -> new NotFoundEventException("Event not found by transaction id")));
    }

    public Optional<Event> getByOrderId(String orderId) {
        if (orderId == null)
            throw new InvalidEventException("Order id cannot be null");

        return Optional.ofNullable(repository.findByOrderId(orderId).orElseThrow(() -> new NotFoundEventException("Event not found by order id")));
    }
}
