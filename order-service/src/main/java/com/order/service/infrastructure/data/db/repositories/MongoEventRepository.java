package com.order.service.infrastructure.data.db.repositories;

import com.order.service.infrastructure.data.db.entities.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongoEventRepository extends MongoRepository<EventEntity, String> {

    List<EventEntity> findAllByOrderByCreatedAtDesc();

    Optional<EventEntity> findTop1ByOrderIdOrderByCreatedAtDesc(String orderId);

    Optional<EventEntity> findTop1ByTransactionIdOrderByCreatedAtDesc(String transactionId);
}
