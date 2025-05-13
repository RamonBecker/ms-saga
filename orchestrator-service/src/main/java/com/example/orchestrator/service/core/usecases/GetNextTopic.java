package com.example.orchestrator.service.core.usecases;

import com.example.orchestrator.service.infrastructure.dto.event.EventDTO;
import com.example.orchestrator.service.infrastructure.shared.constants.Topic;

public interface GetNextTopic {
    Topic execute(EventDTO eventDTO);
}
