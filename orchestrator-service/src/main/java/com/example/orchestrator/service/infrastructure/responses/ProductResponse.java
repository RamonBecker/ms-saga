package com.example.orchestrator.service.infrastructure.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String code;
    private double unitValue;


}
