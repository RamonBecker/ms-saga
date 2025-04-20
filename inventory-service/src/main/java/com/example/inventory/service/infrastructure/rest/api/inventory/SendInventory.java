package com.example.inventory.service.infrastructure.rest.api.inventory;

import com.example.inventory.service.core.domain.Event;
import com.example.inventory.service.core.usecases.inventory.SaveInventory;
import com.example.inventory.service.core.usecases.orderInventory.RollbackOrderInventory;
import com.example.inventory.service.core.usecases.orderInventory.SaveOrderInventory;
import com.example.inventory.service.infrastructure.dto.EventDTO;
import com.example.inventory.service.infrastructure.rest.api.producer.ProducerTopic;
import com.example.inventory.service.infrastructure.serializers.JsonSerializer;
import lombok.extern.slf4j.Slf4j;

import static com.example.inventory.service.infrastructure.shared.constants.CommonConstants.CURRENT_SOURCE;
import static com.example.inventory.service.infrastructure.shared.constants.SagaStatus.*;

@Slf4j
public class SendInventory {

    private final SaveOrderInventory saveOrderInventory;
    private final SaveInventory saveInventory;
    private final RollbackOrderInventory rollbackOrderInventory;
    private final ProducerTopic producer;
    private final JsonSerializer serializer;

    public SendInventory(SaveOrderInventory saveOrderInventory, SaveInventory saveInventory, RollbackOrderInventory rollbackOrderInventory, ProducerTopic producer, JsonSerializer serializer) {
        this.saveOrderInventory = saveOrderInventory;
        this.saveInventory = saveInventory;
        this.rollbackOrderInventory = rollbackOrderInventory;
        this.producer = producer;
        this.serializer = serializer;
    }

    public void success(EventDTO eventDTO) throws Exception {

        var event = Event.fromDomain(eventDTO);
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

        producer.send(serializer.toJson(event));
    }

    public void rollback(EventDTO dto) throws Exception {

        var event = Event.fromDomain(dto);
        event.setStatus(String.valueOf(FAIL));
        event.setSource(CURRENT_SOURCE);

        var order = event.getOrder();

        try {
            rollbackOrderInventory.execute(order);
            event.addHistory("Rollback executed for inventory!");
        } catch (Exception e) {
            event.addHistory("Rollback not executed for inventory: ".concat(e.getMessage()));
        }

        producer.send(serializer.toJson(event));
    }
}
