package com.product.validation.service.infrastructure.dto.event;


import com.product.validation.service.infrastructure.shared.constants.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
