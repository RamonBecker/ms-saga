package com.example.orchestrator.service.infrastructure.saga.usecases;

import com.example.orchestrator.service.infrastructure.dto.event.EventDTO;
import com.example.orchestrator.service.infrastructure.shared.constants.Topic;

public interface SagaEventHandler {
    void handle(Topic topic, EventDTO event) throws Exception;
}
