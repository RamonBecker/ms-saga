package com.order.service.core.usecases.event.impl;

import com.order.service.core.domain.event.Event;
import com.order.service.core.ports.EventRepositoryPort;
import com.order.service.core.usecases.event.GetEventByTransaction;
import com.order.service.core.usecases.event.exception.InvalidEventException;
import com.order.service.core.usecases.event.exception.NotFoundEventException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;


@NoArgsConstructor
@AllArgsConstructor
public class GetEventByTransactionImpl implements GetEventByTransaction {

    private EventRepositoryPort repository;

    @Override
    public Optional<Event> execute(String transactionId) {
        if (transactionId == null)
            throw new InvalidEventException("Transaction id cannot be null");

        return Optional.ofNullable(repository.findByTransactionId(transactionId).orElseThrow(() -> new NotFoundEventException("Event not found by transaction id")));
    }
}
