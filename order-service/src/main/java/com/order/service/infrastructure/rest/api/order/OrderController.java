package com.order.service.infrastructure.rest.api.order;

import com.order.service.core.domain.Order;
import com.order.service.core.usecases.order.CreateOrder;
import com.order.service.infrastructure.rest.api.dto.order.OrderDTO;
import com.order.service.infrastructure.rest.api.dto.order.OrderProductFilterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    private final CreateOrder createOrder;

    public OrderController(CreateOrder createOrder) {
        this.createOrder = createOrder;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderProductFilterDTO response) {

        var savedOrder = createOrder.execute(Order.fromFilterResponse(response));

        return ResponseEntity.status(CREATED).body(OrderDTO.from(savedOrder));
    }
}
