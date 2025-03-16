package com.example.orchestrator.service.infrastructure.responses;


import com.order.service.infrastructure.response.ProductResponse;
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
