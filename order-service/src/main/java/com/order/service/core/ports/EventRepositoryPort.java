package com.order.service.core.ports;

import com.order.service.core.domain.event.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {

    Event save(Event event);

    List<Event> getAll();

    Optional<Event> findByTransactionId(String transactionId);

    Optional<Event> findByOrderId(String orderId);
}
