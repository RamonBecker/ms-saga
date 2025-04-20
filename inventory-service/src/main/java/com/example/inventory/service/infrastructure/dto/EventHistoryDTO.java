package com.example.inventory.service.infrastructure.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventHistoryDTO {

    private String source;
    private String status;
    private String message;
    private LocalDateTime createdAt;

}
