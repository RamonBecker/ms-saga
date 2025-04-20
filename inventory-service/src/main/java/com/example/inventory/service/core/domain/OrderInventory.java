package com.example.inventory.service.core.domain;

import com.example.inventory.service.infrastructure.data.db.entities.OrderInventoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInventory {

    private Integer id;

    private Inventory inventory;

    private String orderId;

    private String transactionId;

    private Integer quantity;

    private Integer oldQuantity;

    private Integer newQuantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static OrderInventory newInstance(Event event, OrderProduct product, Inventory inventory) {
        return OrderInventory
                .builder()
                .inventory(inventory)
                .oldQuantity(inventory.getAvailable())
                .quantity(product.getQuantity())
                .newQuantity(inventory.getAvailable() - product.getQuantity())
                .orderId(event.getOrder().getId())
                .transactionId(event.getTransactionId())
                .build();
    }

    public static OrderInventory fromDomain(OrderInventoryEntity entity) {
        return OrderInventory.builder()
                .id(entity.getId())
                .inventory(Inventory.fromDomain(entity.getInventory()))
                .orderId(entity.getOrderId())
                .transactionId(entity.getTransactionId())
                .quantity(entity.getQuantity())
                .oldQuantity(entity.getOldQuantity())
                .newQuantity(entity.getNewQuantity())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
