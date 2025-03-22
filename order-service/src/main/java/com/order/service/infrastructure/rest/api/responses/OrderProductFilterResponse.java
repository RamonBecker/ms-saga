package com.order.service.infrastructure.rest.api.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderProductFilterResponse {
    private List<OrderProductResponse> products;

}
