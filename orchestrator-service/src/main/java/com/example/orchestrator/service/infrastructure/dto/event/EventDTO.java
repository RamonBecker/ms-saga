package com.example.orchestrator.service.infrastructure.dto.event;


import com.example.orchestrator.service.infrastructure.dto.order.OrderDTO;
import com.example.orchestrator.service.infrastructure.shared.constants.Source;
import com.example.orchestrator.service.infrastructure.shared.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private String id;
    private String transactionId;
    private String orderId;
    private OrderDTO order;
    private Source source;
    private Status status;
    private List<EventHistoryDTO> histories;
    private LocalDateTime createdAt;


    public void addHistory(String message) {
        if (histories == null)
            histories = new ArrayList<>();

        createdAt = LocalDateTime.now();

        histories.add(
                EventHistoryDTO.builder()
                        .source(getSource())
                        .status(getStatus())
                        .message(message)
                        .createdAt(LocalDateTime.now()).build());
    }

    public void setSourceAndStatus(Source source, Status status) {
        this.setSource(source);
        this.setStatus(status);
    }
}
