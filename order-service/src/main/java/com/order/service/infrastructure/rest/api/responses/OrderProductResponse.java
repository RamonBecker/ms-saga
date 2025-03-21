package com.order.service.infrastructure.rest.api.responses;

import com.order.service.core.domain.OrderProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderProductResponse {
    private List<OrderProduct> products;

}
