package com.order.service.infrastructure.rest.api.event;

import com.order.service.core.usecases.event.GetAllEvent;
import com.order.service.core.usecases.event.GetEventByOrder;
import com.order.service.core.usecases.event.GetEventByTransaction;
import com.order.service.core.usecases.event.impl.GetAllEventImpl;
import com.order.service.infrastructure.rest.api.dto.event.EventDTO;
import com.order.service.infrastructure.rest.api.dto.event.EventFilterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final GetAllEvent getEvent;
    private final GetEventByTransaction getEventByTransactionId;
    private final GetEventByOrder getEventByOrderId;


    public EventController(GetAllEventImpl getEvent, GetEventByTransaction getEventByTransactionId, GetEventByOrder getEventByOrderId) {
        this.getEvent = getEvent;
        this.getEventByTransactionId = getEventByTransactionId;
        this.getEventByOrderId = getEventByOrderId;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventDTO>> getAll() {
        return ResponseEntity.status(OK).body(getEvent.execute().stream().map(EventDTO::from).toList());
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getByTransactionOrOrder(EventFilterDTO filter) {
        if (!isEmpty(filter.getOrderId()))
            return ResponseEntity.status(OK).body(getEventByTransactionId.execute(filter.getOrderId()).stream().map(EventDTO::from).toList());

        return ResponseEntity.status(OK).body(getEventByOrderId.execute(filter.getTransactionId()).stream().map(EventDTO::from).toList());
    }
}
