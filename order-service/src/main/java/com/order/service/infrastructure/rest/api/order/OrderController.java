package com.order.service.infrastructure.rest.api.order;

import com.order.service.core.domain.Order;
import com.order.service.core.usecases.order.CreateOrder;
import com.order.service.infrastructure.rest.api.responses.OrderProductFilterResponse;
import com.order.service.infrastructure.rest.api.responses.OrderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    private final CreateOrder createOrder;

    public OrderController(CreateOrder createOrder) {
        this.createOrder = createOrder;
    }

    @PostMapping
    public OrderResponse create(@RequestBody OrderProductFilterResponse response) {

        var savedOrder = createOrder.execute(Order.fromFilterResponse(response));

        return OrderResponse.from(savedOrder);
    }
}
