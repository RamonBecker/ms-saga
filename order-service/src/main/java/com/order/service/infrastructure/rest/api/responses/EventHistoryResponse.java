package com.order.service.infrastructure.rest.api.responses;


import com.order.service.core.domain.EventHistory;
import com.order.service.infrastructure.shared.constants.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHistoryResponse {

    private String source;
    private SagaStatus status;
    private String message;
    private LocalDateTime createdAt;

    public static EventHistoryResponse from(EventHistory history) {
        return EventHistoryResponse.builder().source(history.getSource())
                .status(history.getStatus())
                .message(history.getMessage())
                .createdAt(history.getCreatedAt())
                .build();

    }
}
