package com.order.service.core.ports;

import com.order.service.core.domain.order.Order;

public interface OrderServiceRepositoryPort {

    Order save(Order order);

}
