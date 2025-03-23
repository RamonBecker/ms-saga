package com.order.service.infrastructure.rest.api.dto.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventFilterDTO {

    private String orderId;
    private String transactionId;

}
