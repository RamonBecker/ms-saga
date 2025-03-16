package com.order.service.infrastructure.data.db.entities;


import com.order.service.infrastructure.shared.constants.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event")
public class Event {

    @Id
    private String id;
    private String transactionId;
    private String orderId;
    private Order order;
    private String source;
    private SagaStatus status;
    private List<EventHistory> histories;
    private LocalDateTime createdAt;
}
