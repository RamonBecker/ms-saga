package com.example.inventory.service.infrastructure.data.db.repositories.impl;

import com.example.inventory.service.core.domain.Inventory;
import com.example.inventory.service.core.ports.InventoryRepositoryPort;
import com.example.inventory.service.infrastructure.data.db.entities.InventoryEntity;
import com.example.inventory.service.infrastructure.data.db.repositories.JpaInventoryRepository;

import java.util.Optional;

public class InventoryRepository implements InventoryRepositoryPort {

    private final JpaInventoryRepository repository;

    public InventoryRepository(JpaInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Inventory> findByProductCode(String productCode) {
        return repository.findByProductCode(productCode).map(Inventory::fromDomain);
    }

    @Override
    public void save(Inventory inventory) {
        repository.save(InventoryEntity.fromEntity(inventory));
    }
}
