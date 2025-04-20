package com.example.inventory.service.infrastructure.data.db.repositories;

import com.example.inventory.service.infrastructure.data.db.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaInventoryRepository extends JpaRepository<InventoryEntity, Integer> {

    Optional<InventoryEntity> findByProductCode(String productCode);
}
