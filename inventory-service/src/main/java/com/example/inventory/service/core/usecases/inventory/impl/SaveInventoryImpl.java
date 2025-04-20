package com.example.inventory.service.core.usecases.inventory.impl;

import com.example.inventory.service.core.domain.Order;
import com.example.inventory.service.core.ports.InventoryRepositoryPort;
import com.example.inventory.service.core.usecases.inventory.SaveInventory;
import com.example.inventory.service.core.usecases.inventory.exception.NotFoundInventoryException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SaveInventoryImpl implements SaveInventory {

    private InventoryRepositoryPort inventoryRepository;

    @Override
    public void execute(Order order) {

        order.getProducts().forEach(product -> {
            var inventory = inventoryRepository.findByProductCode(product.getProduct().getCode());

            if (inventory.isEmpty())
                throw new NotFoundInventoryException("Inventory cannot be found!");

            inventory.get().setQuantityAvailable(product.getQuantity());

            inventoryRepository.save(inventory.get());
        });
    }
}
