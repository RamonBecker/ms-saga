package com.order.service.infrastructure.data.db.entities;


import com.order.service.core.domain.Event;
import com.order.service.core.domain.EventHistory;
import com.order.service.core.domain.Order;
import com.order.service.infrastructure.shared.constants.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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
    private SagaStatus status;
    private List<EventHistoryEntity> histories;
    private LocalDateTime createdAt;


    public Event setOrder(Order order) {
        return Event
                .builder()
                .orderId(order.getId())
                .transactionId(order.getTransactionId())
                .order(order)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static EventEntity from(Event event) {

        return EventEntity.builder()
                .id(event.getId())
                .transactionId(event.getTransactionId())
                .orderId(event.getOrderId())
                .source(event.getSource())
                .histories(EventEntity.toHistoriesEntities(event.getHistories()))
                .status(SagaStatus.valueOf(event.getStatus()))
                .createdAt(event.getCreatedAt()).build();
    }

    public static List<EventHistoryEntity> toHistoriesEntities(List<EventHistory> histories) {
        return histories.stream()
                .map(EventHistoryEntity::from)
                .collect(Collectors.toList());
    }

}
