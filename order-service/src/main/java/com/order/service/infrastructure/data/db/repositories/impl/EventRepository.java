package com.order.service.infrastructure.data.db.repositories.impl;

import com.order.service.core.domain.Event;
import com.order.service.core.ports.EventRepositoryPort;
import com.order.service.infrastructure.data.db.entities.EventEntity;
import com.order.service.infrastructure.data.db.repositories.MongoEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class EventRepository implements EventRepositoryPort {

    private MongoEventRepository repository;

    public EventRepository(MongoEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event save(Event event) {
        return Event.from(repository.save(EventEntity.from(event)));
    }

    @Override
    public List<Event> getAll() {

        var t = repository.findAllByOrderByCreatedAtDesc();

        return repository.findAllByOrderByCreatedAtDesc().stream().map(Event::from).toList();
    }

    @Override
    public Optional<Event> findByTransactionId(String transactionId) {
        return repository.findTop1ByTransactionIdOrderByCreatedAtDesc(transactionId).map(Event::from);
    }

    @Override
    public Optional<Event> findByOrderId(String orderId) {
        return repository.findTop1ByOrderIdOrderByCreatedAtDesc(orderId).map(Event::from);
    }

    public void notifyEnding(Event event) {
        event.setOrderId(event.getOrder().getId());
        event.setCreatedAt(LocalDateTime.now());
        save(event);
        log.info("Order {} with saga notified! TransactionId: {}", event.getOrderId(), event.getTransactionId());
    }
}
