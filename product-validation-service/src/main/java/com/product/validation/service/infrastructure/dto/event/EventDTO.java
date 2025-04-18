package com.product.validation.service.infrastructure.dto.event;


import com.product.validation.service.infrastructure.dto.order.OrderDTO;
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
    private String source;
    private String status;
    private List<EventHistoryDTO> histories;
    private LocalDateTime createdAt;

}
