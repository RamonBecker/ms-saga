package com.example.inventory.service.infrastructure.data.db.repositories;

import com.example.inventory.service.infrastructure.data.db.entities.OrderInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderInventoryRepository extends JpaRepository<OrderInventoryEntity, Integer> {

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);
    List<OrderInventoryEntity> findByOrderIdAndTransactionId(String orderId, String transactionId);
}
