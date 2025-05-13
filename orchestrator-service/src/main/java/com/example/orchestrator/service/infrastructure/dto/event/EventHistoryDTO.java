package com.example.orchestrator.service.infrastructure.dto.event;


import com.example.orchestrator.service.infrastructure.shared.constants.Source;
import com.example.orchestrator.service.infrastructure.shared.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventHistoryDTO {

    private Source source;
    private Status status;
    private String message;
    private LocalDateTime createdAt;

}
