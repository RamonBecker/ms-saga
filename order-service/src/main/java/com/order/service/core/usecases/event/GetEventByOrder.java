package com.order.service.core.usecases.event;

import com.order.service.core.domain.event.Event;

import java.util.Optional;

public interface GetEventByOrder {

    Optional<Event> execute(String orderId);

}
