package com.payment.service.infrastructure.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResponse {

    private ProductResponse product;
    private int quantity;

}
