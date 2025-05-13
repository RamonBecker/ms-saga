package com.order.service.core.usecases.event;

import com.order.service.core.domain.event.Event;

public interface CreateEvent {

    Event execute(Event event);
}
