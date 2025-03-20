package com.order.service.core.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
public class Event {

    private String id;
    private String transactionId;
    private String orderId;
    private Order order;
    private String source;
    private String status;
    private List<EventHistory> histories;
    private LocalDateTime createdAt;
}
