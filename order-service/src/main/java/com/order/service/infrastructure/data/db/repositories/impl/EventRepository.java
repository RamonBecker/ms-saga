package com.order.service.infrastructure.data.db.repositories.impl;

import com.order.service.core.domain.Event;
import com.order.service.core.ports.EventRepositoryPort;
import com.order.service.infrastructure.data.db.entities.EventEntity;
import com.order.service.infrastructure.data.db.repositories.MongoEventRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EventRepository implements EventRepositoryPort {

    private MongoEventRepository repository;

    @Override
    public Event save(Event event) {
        return Event.fromEntity(repository.save(EventEntity.fromEvent(event)));
    }

    @Override
    public List<Event> getAll() {

        return repository.findAllByOrderByCreatedAtDesc().stream().map(Event::fromEntity).toList();
    }

    @Override
    public Optional<Event> findByTransactionId(String transactionId) {
        return repository.findTop1ByTransactionIdOrderByCreatedAtDesc(transactionId).map(Event::fromEntity);
    }

    @Override
    public Optional<Event> findByOrderId(String orderId) {
        return repository.findTop1ByOrderIdOrderByCreatedAtDesc(orderId).map(Event::fromEntity);
    }

    public void notifyEnding(Event event) {
        event.setOrderId(event.getOrder().getId());
        event.setCreatedAt(LocalDateTime.now());
        save(event);
        log.info("Order {} with saga notified! TransactionId: {}", event.getOrderId(), event.getTransactionId());
    }
}
