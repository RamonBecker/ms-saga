package com.order.service.infrastructure.rest.api.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventFilterResponse {

    private String orderId;
    private String transactionId;

}
