package com.example.inventory.service.infrastructure.data.db.repositories.impl;

import com.example.inventory.service.core.domain.inventory.OrderInventory;
import com.example.inventory.service.core.ports.OrderInventoryRepositoryPort;
import com.example.inventory.service.infrastructure.data.db.entities.OrderInventoryEntity;
import com.example.inventory.service.infrastructure.data.db.repositories.JpaOrderInventoryRepository;

import java.util.List;

public class OrderInventoryRepository implements OrderInventoryRepositoryPort {

    private final JpaOrderInventoryRepository repository;

    public OrderInventoryRepository(JpaOrderInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId) {
        return repository.existsByOrderIdAndTransactionId(orderId, transactionId);
    }

    @Override
    public void save(OrderInventory order) {
        repository.save(OrderInventoryEntity.fromEntity(order));
    }

    @Override
    public List<OrderInventory> findByOrderIdAndTransactionId(String orderId, String transactionId) {
        return repository.findByOrderIdAndTransactionId(orderId, transactionId)
                .stream()
                .map(OrderInventory::fromDomain)
                .toList();
    }
}
