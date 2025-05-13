package com.order.service.core.domain.event;


import com.order.service.infrastructure.data.db.entities.EventHistoryEntity;
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


    public static EventHistory fromDomain(EventHistoryEntity historyEntity) {
        return EventHistory.builder().source(historyEntity.getSource())
                .status(historyEntity.getStatus())
                .message(historyEntity.getMessage())
                .createdAt(historyEntity.getCreatedAt())
                .build();
    }

}
