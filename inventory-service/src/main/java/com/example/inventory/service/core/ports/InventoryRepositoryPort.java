package com.example.inventory.service.core.ports;

import com.example.inventory.service.core.domain.Inventory;

import java.util.Optional;

public interface InventoryRepositoryPort {

    Optional<Inventory> findByProductCode(String productCode);

    void save(Inventory inventory);
}
