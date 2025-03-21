package com.order.service.core.usecases.order;

import com.order.service.core.domain.Order;

public interface CreateOrder {

    Order create(Order order);
}
