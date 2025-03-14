package com.example.inventory.service.infrastructure.response;


import com.example.inventory.service.infrastructure.shared.constants.SagaStatus;
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
public class EventResponse {


    private String id;
    private String transactionId;
    private String orderId;
    private String source;
    private SagaStatus status;
    private LocalDateTime createdAt;
    private OrderResponse order;
    private List<EventHistoryResponse> histories;
}
