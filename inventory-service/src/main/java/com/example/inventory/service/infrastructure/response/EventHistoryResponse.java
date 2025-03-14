package com.example.inventory.service.infrastructure.response;


import com.example.inventory.service.infrastructure.constants.SagaStatus;
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

    private String source;
    private SagaStatus status;
    private String message;
    private LocalDateTime createdAt;

}
