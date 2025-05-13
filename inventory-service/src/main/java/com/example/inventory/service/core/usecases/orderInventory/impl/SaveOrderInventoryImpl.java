package com.example.inventory.service.core.usecases.orderInventory.impl;

import com.example.inventory.service.core.domain.event.Event;
import com.example.inventory.service.core.domain.inventory.OrderInventory;
import com.example.inventory.service.core.ports.InventoryRepositoryPort;
import com.example.inventory.service.core.ports.OrderInventoryRepositoryPort;
import com.example.inventory.service.core.usecases.inventory.exception.NotFoundInventoryException;
import com.example.inventory.service.core.usecases.orderInventory.SaveOrderInventory;
import com.example.inventory.service.core.usecases.orderInventory.exception.OrderInventoryExistsException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SaveOrderInventoryImpl implements SaveOrderInventory {

    private OrderInventoryRepositoryPort orderRepository;
    private InventoryRepositoryPort inventoryRepository;

    @Override
    public void execute(Event event) {
        if (orderRepository.existsByOrderIdAndTransactionId(event.getOrderId(), event.getTransactionId()))
            throw new OrderInventoryExistsException("There's another transactionId for this validation!");

        event.getOrder().getProducts().forEach(orderProduct -> {

            var inventory = inventoryRepository.findByProductCode(orderProduct.getProduct().getCode());

            if (inventory.isEmpty())
                throw new NotFoundInventoryException("Inventory cannot be found!");

            orderRepository.save(
                    OrderInventory.newInstance(event, orderProduct, inventory.get())
            );
        });
    }
}
