package com.example.orchestrator.service.infrastructure.responses;


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
