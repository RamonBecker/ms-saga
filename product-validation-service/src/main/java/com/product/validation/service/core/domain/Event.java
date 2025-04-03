package com.product.validation.service.core.domain;


import com.product.validation.service.infrastructure.dto.event.EventDTO;
import com.product.validation.service.infrastructure.dto.event.EventHistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

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

    public static Event from(EventDTO dto) {
        return Event.builder()
                .id(dto.getId())
                .transactionId(dto.getTransactionId())
                .orderId(dto.getOrderId())
                .order(Order.fromOrder(dto.getOrder()))
                .source(dto.getSource())
                .status(dto.getStatus())
                .histories(Event.toHistories(dto.getHistories()))
                .build();
    }

    public static List<EventHistory> toHistories(List<EventHistoryDTO> histories) {

        if (histories == null)
            return new ArrayList<>();

        return histories.stream()
                .map(EventHistory::fromDomain)
                .collect(Collectors.toList());
    }
}
