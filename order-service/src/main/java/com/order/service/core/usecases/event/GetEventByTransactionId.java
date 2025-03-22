package com.order.service.core.usecases.event;

import com.order.service.core.domain.Event;

import java.util.Optional;

public interface GetEventByTransactionId {

    Optional<Event> execute(String transactionId);

}
