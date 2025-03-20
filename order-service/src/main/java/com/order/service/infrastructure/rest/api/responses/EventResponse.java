package com.order.service.infrastructure.rest.api.responses;

import com.order.service.core.domain.EventHistory;
import com.order.service.core.domain.Order;

import java.time.LocalDateTime;
import java.util.List;

public class EventResponse {

    private String id;
    private String transactionId;
    private String orderId;
    private Order order;
    private String source;
    private String status;
    private List<EventHistory> histories;
    private LocalDateTime createdAt;
}
