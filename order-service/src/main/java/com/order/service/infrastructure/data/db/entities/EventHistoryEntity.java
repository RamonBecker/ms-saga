package com.order.service.infrastructure.data.db.entities;


import com.order.service.core.domain.event.EventHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventHistoryEntity {

    private String source;
    private String status;
    private String message;
    private LocalDateTime createdAt;


    public static EventHistoryEntity fromEntity(EventHistory history) {
        return EventHistoryEntity.builder().source(history.getSource())
                .status(history.getStatus())
                .message(history.getMessage())
                .createdAt(history.getCreatedAt())
                .build();

    }

}
