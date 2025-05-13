package com.example.inventory.service.core.usecases.inventory;

import com.example.inventory.service.core.domain.order.Order;

public interface SaveInventory {

    void execute(Order order);
}
