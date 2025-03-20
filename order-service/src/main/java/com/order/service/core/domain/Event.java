package com.order.service.core.domain;


import com.order.service.infrastructure.data.db.entities.EventEntity;
import com.order.service.infrastructure.data.db.entities.EventHistoryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Setter
@Getter
public class Event {

    private String id;
    private String transactionId;
    private String orderId;
    private Order order;
    private String source;
    private String status;
    private List<EventHistory> histories;
    private LocalDateTime createdAt;

    public static List<EventHistory> toHistories(List<EventHistoryEntity> histories) {
        return histories.stream()
                .map(EventHistory::from)
                .collect(Collectors.toList());
    }

    public static Event from(EventEntity entity) {

        return Event.builder()
                .id(entity.getId())
                .transactionId(entity.getTransactionId())
                .orderId(entity.getOrderId())
                .source(entity.getSource())
                .histories(Event.toHistories(entity.getHistories()))
                .status(String.valueOf(entity.getStatus()))
                .createdAt(entity.getCreatedAt()).build();

    }
}
