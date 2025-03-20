package com.order.service.infrastructure.rest.api.responses;

import com.order.service.infrastructure.data.db.entities.OrderProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private String id;
    private LocalDateTime createdAt;
    private String transactionId;
    private double totalAmount;
    private List<OrderProductEntity> products;




}
