package com.order.service.infrastructure.rest.api.responses;

import com.order.service.core.domain.Event;
import com.order.service.core.domain.EventHistory;
import com.order.service.core.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {

    private String id;
    private String transactionId;
    private String orderId;
    private OrderResponse order;
    private String source;
    private String status;
    private List<EventHistoryResponse> histories;
    private LocalDateTime createdAt;

    public static EventResponse from(Event event) {

        return EventResponse.builder()
                .id(event.getId())
                .transactionId(event.getTransactionId())
                .orderId(event.getOrderId())
                .order(OrderResponse.from(event.getOrder()))
                .source(event.getSource())
                .histories(EventResponse.toHistories(event.getHistories()))
                .status(event.getStatus())
                .createdAt(event.getCreatedAt()).build();
    }

    public static List<EventHistoryResponse> toHistories(List<EventHistory> histories) {

        if (histories == null)
            return new ArrayList<>();

        return histories.stream()
                .map(EventHistoryResponse::from)
                .collect(Collectors.toList());
    }
}
