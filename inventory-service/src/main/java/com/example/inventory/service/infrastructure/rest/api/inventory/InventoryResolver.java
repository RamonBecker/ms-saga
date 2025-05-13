package com.example.inventory.service.infrastructure.rest.api.inventory;

import com.example.inventory.service.core.domain.event.Event;
import com.example.inventory.service.core.usecases.inventory.SaveInventory;
import com.example.inventory.service.core.usecases.orderInventory.RollbackOrderInventory;
import com.example.inventory.service.core.usecases.orderInventory.SaveOrderInventory;
import com.example.inventory.service.infrastructure.rest.api.publisher.EventPublisher;
import lombok.extern.slf4j.Slf4j;

import static com.example.inventory.service.infrastructure.shared.constants.CommonConstants.CURRENT_SOURCE;
import static com.example.inventory.service.infrastructure.shared.constants.Status.*;

@Slf4j
public class InventoryResolver {

    private final SaveOrderInventory saveOrderInventory;
    private final SaveInventory saveInventory;
    private final RollbackOrderInventory rollbackOrderInventory;
    private final EventPublisher publisher;

    public InventoryResolver(SaveOrderInventory saveOrderInventory, SaveInventory saveInventory, RollbackOrderInventory rollbackOrderInventory, EventPublisher publisher) {
        this.saveOrderInventory = saveOrderInventory;
        this.saveInventory = saveInventory;
        this.rollbackOrderInventory = rollbackOrderInventory;
        this.publisher = publisher;
    }

    public void success(Event event) throws Exception {

        event.setSource(CURRENT_SOURCE);

        try {

            saveOrderInventory.execute(event);
            saveInventory.execute(event.getOrder());

            event.setStatus(String.valueOf(SUCCESS));
            event.addHistory("Inventory updated successfully!");

        } catch (Exception e) {
            log.error("Error trying to update inventory: ", e);
            event.setStatus(String.valueOf(ROLLBACK_PENDING));
            event.addHistory("Fail to update inventory: ".concat(e.getMessage()));
        }

        publisher.publish(event);
    }

    public void rollback(Event event) throws Exception {

        event.setStatus(String.valueOf(FAIL));
        event.setSource(CURRENT_SOURCE);

        var order = event.getOrder();

        try {
            rollbackOrderInventory.execute(order);
            event.addHistory("Rollback executed for inventory!");
        } catch (Exception e) {
            event.addHistory("Rollback not executed for inventory: ".concat(e.getMessage()));
        }

        publisher.publish(event);
    }
}
