package com.order.service.infrastructure.data.db.entities;


import com.order.service.core.domain.event.Event;
import com.order.service.core.domain.event.EventHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event")
public class EventEntity {

    @Id
    private String id;
    private String transactionId;
    private String orderId;
    private OrderEntity order;
    private String source;
    private String status;
    private List<EventHistoryEntity> histories;
    private LocalDateTime createdAt;

    public static EventEntity fromEvent(Event event) {
        return EventEntity.builder()
                .id(event.getId())
                .transactionId(event.getTransactionId())
                .orderId(event.getOrderId())
                .order(OrderEntity.fromEntity(event.getOrder()))
                .source(event.getSource())
                .histories(EventEntity.toHistoriesEntities(event.getHistories()))
                .status(event.getStatus())
                .createdAt(event.getCreatedAt()).build();
    }

    public static List<EventHistoryEntity> toHistoriesEntities(List<EventHistory> histories) {

        if (histories == null) return new ArrayList<>();

        return histories.stream()
                .map(EventHistoryEntity::fromEntity)
                .collect(Collectors.toList());
    }

}
