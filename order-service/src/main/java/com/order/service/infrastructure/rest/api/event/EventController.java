package com.order.service.infrastructure.rest.api.event;

import com.order.service.core.usecases.event.GetEvent;
import com.order.service.core.usecases.event.GetEventImpl;
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

    private final GetEvent getEvent;


    public EventController( GetEventImpl getEvent) {
        this.getEvent = getEvent;
    }

    @GetMapping("/all")
    public List<EventResponse> getAll() {

        return getEvent.getAll().stream().map(EventResponse::from).toList();
    }

    @GetMapping
    public  List<EventResponse> getTransactionOrOrder(EventFilterResponse filter) {
        if (!isEmpty(filter.getOrderId()))
            return getEvent.getByOrderId(filter.getOrderId()).stream().map(EventResponse::from).toList();

        return getEvent.getByTransactionId(filter.getTransactionId()).stream().map(EventResponse::from).toList();
    }
}
