package com.order.service.core.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventHistory {

    private String source;
    private String status;
    private String message;
    private LocalDateTime createdAt;


}
