package com.order.service.infrastructure.rest.api.event;

import com.order.service.core.usecases.event.CreateEvent;
import com.order.service.core.usecases.event.GetEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final CreateEvent createEvent;
    private final GetEvent getEvent;

    public EventController(CreateEvent createEvent, GetEvent getEvent) {
        this.createEvent = createEvent;
        this.getEvent = getEvent;
    }

//    @GetMapping("/all")
//    public List<EventResponse> getAll() {
//
//        var events = getEvent.getAll();
//
//        return eventConverter.eventToEventResponses(events);
//    }
//
//    @GetMapping
//    public List<EventResponse> getTransactionIdOrOrderId(EventFilterResponse filter) {
//
//        List<Event> events = null;
//
//        if (!isEmpty(filter.getOrderId()))
//            events = getEvent.getByOrderId(filter.getOrderId()).stream().toList();
//        else
//            events = getEvent.getByTransactionId(filter.getTransactionId()).stream().toList();
//
//        return eventConverter.eventToEventResponses(events);
//
//    }
}
