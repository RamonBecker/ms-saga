package com.order.service.infrastructure.rest.api.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderProductFilterDTO {
    private List<OrderProductDTO> products;

}
