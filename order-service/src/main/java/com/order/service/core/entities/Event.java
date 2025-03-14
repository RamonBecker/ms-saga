package com.order.service.core.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {


    private String id;
    private String transactionId;
    private String orderId;
    private String source;
    private String status;
    private LocalDateTime createdAt;
    private Order order;
    private List<EventHistory> histories;
}
