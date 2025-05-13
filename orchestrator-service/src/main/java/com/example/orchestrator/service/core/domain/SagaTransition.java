package com.example.orchestrator.service.core.domain;

import com.example.orchestrator.service.infrastructure.shared.constants.Source;
import com.example.orchestrator.service.infrastructure.shared.constants.Status;
import com.example.orchestrator.service.infrastructure.shared.constants.Topic;

public record SagaTransition(Source source, Status status, Topic topic) {

    public boolean matches(Source source, Status status) {
        return this.source == source && this.status == status;
    }
}
