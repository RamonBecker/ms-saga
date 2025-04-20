package com.example.inventory.service.core.usecases.orderInventory;

import com.example.inventory.service.core.domain.Order;

public interface RollbackOrderInventory {

    void execute(Order order);
}
