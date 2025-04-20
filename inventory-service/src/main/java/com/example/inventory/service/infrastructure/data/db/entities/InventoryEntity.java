package com.example.inventory.service.infrastructure.data.db.entities;

import com.example.inventory.service.core.domain.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory")
@Builder
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private Integer available;


    public static InventoryEntity fromEntity(Inventory inventory) {
        return InventoryEntity.builder()
                .id(inventory.getId())
                .productCode(inventory.getProductCode())
                .available(inventory.getAvailable())
                .build();
    }
}
