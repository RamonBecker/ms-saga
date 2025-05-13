package com.order.service.infrastructure.rest.api.dto.event;


import com.order.service.core.domain.event.EventHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventHistoryDTO {

    private String source;
    private String status;
    private String message;
    private LocalDateTime createdAt;

    public static EventHistoryDTO from(EventHistory history) {
        return EventHistoryDTO.builder().source(history.getSource())
                .status(history.getStatus())
                .message(history.getMessage())
                .createdAt(history.getCreatedAt())
                .build();

    }
}
