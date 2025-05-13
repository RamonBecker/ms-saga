package com.payment.service.core.domain.event;


import com.payment.service.infrastructure.dto.event.EventHistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventHistory {

    private String source;
    private String status;
    private String message;
    private LocalDateTime createdAt;

    public static EventHistory fromDomain(EventHistoryDTO dto) {
        return EventHistory.builder().source(dto.getSource())
                .status(dto.getStatus())
                .message(dto.getMessage())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
