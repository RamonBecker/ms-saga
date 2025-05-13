package com.example.inventory.service.core.usecases.orderInventory.impl;

import com.example.inventory.service.core.domain.order.Order;
import com.example.inventory.service.core.ports.InventoryRepositoryPort;
import com.example.inventory.service.core.ports.OrderInventoryRepositoryPort;
import com.example.inventory.service.core.usecases.orderInventory.RollbackOrderInventory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RollbackOrderInventoryImpl implements RollbackOrderInventory {

    private OrderInventoryRepositoryPort orderInventoryRepository;
    private InventoryRepositoryPort inventoryRepository;

    @Override
    public void execute(Order order) {
        orderInventoryRepository
                .findByOrderIdAndTransactionId(order.getId(), order.getTransactionId())
                .forEach(orderInventory -> {
                    var inventory = orderInventory.getInventory();
                    inventory.setAvailable(orderInventory.getOldQuantity());
                    inventoryRepository.save(inventory);
                });
        ;
    }
}
