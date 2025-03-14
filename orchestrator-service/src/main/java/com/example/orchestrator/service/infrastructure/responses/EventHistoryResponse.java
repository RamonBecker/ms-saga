package com.example.orchestrator.service.infrastructure.responses;


import com.example.orchestrator.service.infrastructure.shared.constants.EventSource;
import com.example.orchestrator.service.infrastructure.shared.constants.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventHistoryResponse {

    private EventSource source;
    private SagaStatus status;
    private String message;
    private LocalDateTime createdAt;


}
