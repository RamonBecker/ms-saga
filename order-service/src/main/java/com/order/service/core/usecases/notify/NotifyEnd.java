package com.order.service.core.usecases.notify;

import com.order.service.core.domain.event.Event;

public interface NotifyEnd {

    void execute(Event event);
}
