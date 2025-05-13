package com.example.inventory.service.core.usecases.orderInventory;

import com.example.inventory.service.core.domain.order.Order;

public interface RollbackOrderInventory {

    void execute(Order order);
}
