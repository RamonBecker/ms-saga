package com.example.orchestrator.service.core.usecases.impl;

import com.example.orchestrator.service.core.domain.SagaTransition;
import com.example.orchestrator.service.core.ports.SagaTransitionRepositoryPort;
import com.example.orchestrator.service.core.usecases.GetNextTopic;
import com.example.orchestrator.service.infrastructure.dto.event.EventDTO;
import com.example.orchestrator.service.infrastructure.shared.constants.Topic;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class GetNextTopicImpl implements GetNextTopic {

    private SagaTransitionRepositoryPort repository;
    @Override
    public Topic execute(EventDTO eventDTO) {

        if (eventDTO.getSource() == null || eventDTO.getStatus() == null)
            throw new IllegalArgumentException("Source and status must be informed.");

        return repository.findBy(eventDTO.getSource(), eventDTO.getStatus())
                .map(SagaTransition::topic)
                .orElseThrow(() -> new IllegalStateException("Topic not found for given source and status."));
    }
}
