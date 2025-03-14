package com.example.orchestrator.service.infrastructure.responses;



import com.example.orchestrator.service.infrastructure.constants.EventSource;
import com.example.orchestrator.service.infrastructure.constants.SagaStatus;
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
    private EventSource source;
    private SagaStatus status;
    private LocalDateTime createdAt;
    private OrderResponse order;
    private List<EventHistoryResponse> histories;
}
