package com.order.service.core.usecases.event;

import com.order.service.core.domain.Event;

import java.util.List;

public interface GetAllEvent {

    List<Event> execute();

}
