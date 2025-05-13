package com.example.orchestrator.service.core.ports;

import com.example.orchestrator.service.core.domain.SagaTransition;
import com.example.orchestrator.service.infrastructure.shared.constants.Source;
import com.example.orchestrator.service.infrastructure.shared.constants.Status;

import java.util.Optional;

public interface SagaTransitionRepositoryPort {

    Optional<SagaTransition> findBy(Source source, Status status);
}
