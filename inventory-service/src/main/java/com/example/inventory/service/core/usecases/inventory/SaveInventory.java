package com.example.inventory.service.core.usecases.inventory;

import com.example.inventory.service.core.domain.Order;

public interface SaveInventory {

    void execute(Order order);
}
