package com.order.service.infrastructure.rest.api.event;

import com.order.service.core.usecases.event.GetAllEvent;
import com.order.service.core.usecases.event.GetEventByOrderId;
import com.order.service.core.usecases.event.GetEventByTransactionId;
import com.order.service.core.usecases.event.impl.GetAllEventImpl;
import com.order.service.infrastructure.rest.api.responses.EventFilterResponse;
import com.order.service.infrastructure.rest.api.responses.EventResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final GetAllEvent getEvent;
    private final GetEventByTransactionId getEventByTransactionId;
    private final GetEventByOrderId getEventByOrderId;


    public EventController(GetAllEventImpl getEvent, GetEventByTransactionId getEventByTransactionId, GetEventByOrderId getEventByOrderId) {
        this.getEvent = getEvent;
        this.getEventByTransactionId = getEventByTransactionId;
        this.getEventByOrderId = getEventByOrderId;
    }

    @GetMapping("/all")
    public List<EventResponse> getAll() {
        return getEvent.execute().stream().map(EventResponse::from).toList();
    }

    @GetMapping
    public List<EventResponse> getTransactionOrOrder(EventFilterResponse filter) {
        if (!isEmpty(filter.getOrderId()))
            return getEventByTransactionId.execute(filter.getOrderId()).stream().map(EventResponse::from).toList();
        return getEventByOrderId.execute(filter.getTransactionId()).stream().map(EventResponse::from).toList();
    }
}
