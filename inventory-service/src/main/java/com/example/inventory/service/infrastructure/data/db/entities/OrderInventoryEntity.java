package com.example.inventory.service.infrastructure.data.db.entities;


import com.example.inventory.service.core.domain.Order;
import com.example.inventory.service.core.domain.OrderInventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_inventory")
@Builder
public class OrderInventoryEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private InventoryEntity inventory;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer oldQuantity;

    @Column(nullable = false)
    private Integer newQuantity;


    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = getDateTimeNow();
        updatedAt = getDateTimeNow();

    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = getDateTimeNow();
    }

    public static OrderInventoryEntity fromEntity(OrderInventory order) {
        return OrderInventoryEntity.builder()
                .id(order.getId())
                .inventory(InventoryEntity.fromEntity(order.getInventory()))
                .orderId(order.getOrderId())
                .transactionId(order.getTransactionId())
                .quantity(order.getQuantity())
                .oldQuantity(order.getOldQuantity())
                .newQuantity(order.getNewQuantity())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    private LocalDateTime getDateTimeNow() {
        return LocalDateTime.now();
    }

}
