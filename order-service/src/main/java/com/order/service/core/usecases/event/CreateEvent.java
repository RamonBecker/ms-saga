package com.order.service.core.usecases.event;

import com.order.service.core.domain.Event;

public interface CreateEvent {

    Event execute(Event event);
}
