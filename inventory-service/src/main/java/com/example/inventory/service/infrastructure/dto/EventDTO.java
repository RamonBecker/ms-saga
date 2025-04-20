package com.example.inventory.service.infrastructure.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {


    private String id;
    private String transactionId;
    private String orderId;
    private OrderDTO order;
    private String source;
    private String status;
    private List<EventHistoryDTO> histories;
    private LocalDateTime createdAt;

    public void addToHistory(EventHistoryDTO history) {
        if (isEmpty(history)) {
            histories = new ArrayList<>();
        }
        histories.add(history);
    }
}
