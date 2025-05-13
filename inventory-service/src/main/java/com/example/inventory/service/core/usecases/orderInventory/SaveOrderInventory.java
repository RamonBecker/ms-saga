package com.example.inventory.service.core.usecases.orderInventory;

import com.example.inventory.service.core.domain.event.Event;

public interface SaveOrderInventory {

    void execute(Event event);
}
