package com.example.orchestrator.service.infrastructure.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;
    private List<OrderProductResponse> ordersProducts;
    private LocalDateTime createdAt;
    private String transactionId;
    private double totalAmount;
    private int totalItems;



}
