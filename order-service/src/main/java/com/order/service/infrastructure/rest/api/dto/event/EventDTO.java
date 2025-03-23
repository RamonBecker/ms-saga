package com.order.service.infrastructure.rest.api.dto.event;

import com.order.service.core.domain.Event;
import com.order.service.core.domain.EventHistory;
import com.order.service.infrastructure.rest.api.dto.order.OrderDTO;
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
public class EventDTO {

    private String id;
    private String transactionId;
    private String orderId;
    private OrderDTO order;
    private String source;
    private String status;
    private List<EventHistoryDTO> histories;
    private LocalDateTime createdAt;

    public static EventDTO from(Event event) {

        return EventDTO.builder()
                .id(event.getId())
                .transactionId(event.getTransactionId())
                .orderId(event.getOrderId())
                .order(OrderDTO.from(event.getOrder()))
                .source(event.getSource())
                .histories(EventDTO.toHistories(event.getHistories()))
                .status(event.getStatus())
                .createdAt(event.getCreatedAt()).build();
    }

    public static List<EventHistoryDTO> toHistories(List<EventHistory> histories) {

        if (histories == null)
            return new ArrayList<>();

        return histories.stream()
                .map(EventHistoryDTO::from)
                .collect(Collectors.toList());
    }
}
