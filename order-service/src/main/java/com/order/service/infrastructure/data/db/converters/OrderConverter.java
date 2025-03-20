package com.order.service.infrastructure.data.db.converters;


import com.order.service.core.domain.Order;
import com.order.service.infrastructure.data.db.entities.OrderEntity;
import com.order.service.infrastructure.rest.api.responses.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderConverter {

    Order orderEntityToOrder(OrderEntity entity);

    OrderEntity orderToOrderEntity(Order order);

    Order orderResponseToOrder(OrderResponse response);

    OrderResponse orderToOrderResponse(Order order);
}
