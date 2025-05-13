package com.order.service.core.usecases.order;

import com.order.service.core.domain.order.Order;

public interface CreateOrder {

    Order execute(Order order);
}
