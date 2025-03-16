package com.product.validation.service.infrastructure.response;


import com.order.service.infrastructure.shared.constants.SagaStatus;
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
    private OrderResponse order;
    private String source;
    private SagaStatus status;
    private List<EventHistoryResponse> histories;
    private LocalDateTime createdAt;
}
