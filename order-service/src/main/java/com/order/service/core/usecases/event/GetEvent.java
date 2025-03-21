package com.order.service.core.usecases.event;

import com.order.service.core.domain.Event;

import java.util.List;
import java.util.Optional;

public interface GetEvent {

    List<Event> getAll();

    Optional<Event> getByTransactionId(String transactionId);

    Optional<Event> getByOrderId(String orderId);
}
