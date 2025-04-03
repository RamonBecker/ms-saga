package com.order.service.core.domain;


import com.order.service.infrastructure.data.db.entities.EventEntity;
import com.order.service.infrastructure.data.db.entities.EventHistoryEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private String id;
    private String transactionId;
    private String orderId;
    private Order order;
    private String source;
    private String status;
    private List<EventHistory> histories;
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


    public static List<EventHistory> toHistories(List<EventHistoryEntity> histories) {

        if (histories == null)
            return new ArrayList<>();

        return histories.stream()
                .map(EventHistory::fromDomain)
                .collect(Collectors.toList());
    }

    public static Event fromEntity(EventEntity entity) {

        return Event.builder()
                .id(entity.getId())
                .transactionId(entity.getTransactionId())
                .orderId(entity.getOrderId())
                .source(entity.getSource())
                .order(Order.fromEntity(entity.getOrder()))
                .histories(Event.toHistories(entity.getHistories()))
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt()).build();

    }
}
