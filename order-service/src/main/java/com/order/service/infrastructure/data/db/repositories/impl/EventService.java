package com.order.service.infrastructure.data.db.repositories.impl;

import com.order.service.core.domain.Event;
import com.order.service.core.ports.EventServiceRepositoryPort;
import com.order.service.infrastructure.data.db.converters.EventConverter;
import com.order.service.infrastructure.data.db.repositories.MongoEventRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
public class EventService implements EventServiceRepositoryPort {

    private final MongoEventRepository repository;
    private final EventConverter eventConverter;

    public EventService(MongoEventRepository repository, EventConverter eventConverter) {
        this.repository = repository;
        this.eventConverter = eventConverter;
    }

    @Override
    public Event save(Event event) {
        return eventConverter.eventEntityToEvent(repository.save(eventConverter.eventToEventEntity(event)));
    }

    @Override
    public List<Event> getAll() {

        return eventConverter.eventEntitiesToEvents(repository.findAllByOrderByCreatedAtDesc());
    }

    @Override
    public Optional<Event> findByTransactionId(String transactionId) {
        return repository.findTop1ByTransactionIdOrderByCreatedAtDesc(transactionId)
                .map(eventConverter::eventEntityToEvent);
    }

    @Override
    public Optional<Event> findByOrderId(String orderId) {
        return repository.findTop1ByOrderIdOrderByCreatedAtDesc(orderId).map(eventConverter::eventEntityToEvent);
    }

    public void notifyEnding(Event event) {
        event.setOrderId(event.getOrder().getId());
        event.setCreatedAt(LocalDateTime.now());
        save(event);
        log.info("Order {} with saga notified! TransactionId: {}", event.getOrderId(), event.getTransactionId());
    }

}
