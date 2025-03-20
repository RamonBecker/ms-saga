package com.order.service.infrastructure.rest.api.order;

import com.order.service.core.usecases.order.CreateOrder;
import com.order.service.infrastructure.data.db.converters.OrderConverter;
import com.order.service.infrastructure.rest.api.responses.OrderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class OrderController {

    private final CreateOrder createOrder;
    private final OrderConverter orderConverter;

    public OrderController(CreateOrder createOrder, OrderConverter orderConverter) {
        this.createOrder = createOrder;
        this.orderConverter = orderConverter;
    }

//    @PostMapping
//    public OrderResponse createOrder(@RequestBody OrderResponse orderResponse) {
//
//        var order = orderConverter.orderResponseToOrder(orderResponse);
//
//        var savedOrder = createOrder.execute(order);
//
//        return orderConverter.orderToOrderResponse(savedOrder);
//    }
}
