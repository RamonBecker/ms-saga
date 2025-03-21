package com.order.service.infrastructure.rest.api.responses;

import com.order.service.core.domain.Event;
import com.order.service.core.domain.EventHistory;
import com.order.service.core.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {

    private String id;
    private String transactionId;
    private String orderId;
    private Order order;
    private String source;
    private String status;
    private List<EventHistory> histories;
    private LocalDateTime createdAt;

    public static EventResponse from(Event event) {

        return EventResponse.builder()
                .id(event.getId())
                .transactionId(event.getTransactionId())
                .orderId(event.getOrderId())
                .source(event.getSource())
//                .histories(event.getHistories())
                .status(event.getStatus())
                .createdAt(event.getCreatedAt()).build();
    }
}
