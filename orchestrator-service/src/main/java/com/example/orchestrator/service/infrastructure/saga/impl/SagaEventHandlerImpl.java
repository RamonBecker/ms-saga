package com.example.orchestrator.service.infrastructure.saga.impl;

import com.example.orchestrator.service.infrastructure.dto.event.EventDTO;
import com.example.orchestrator.service.infrastructure.saga.usecases.SagaEventHandler;
import com.example.orchestrator.service.infrastructure.saga.usecases.SagaResolver;
import com.example.orchestrator.service.infrastructure.shared.constants.Topic;

public class SagaEventHandlerImpl implements SagaEventHandler {

    private final SagaResolver sagaResolver;

    public SagaEventHandlerImpl(SagaResolver sagaResolver) {
        this.sagaResolver = sagaResolver;
    }

    @Override
    public void handle(Topic topic, EventDTO event) throws Exception {
        switch (topic) {
            case START_SAGA -> sagaResolver.onStart(event);
            case CONTINUE_SAGA -> sagaResolver.onContinue(event);
            case FINISH_SUCCESS -> sagaResolver.finishSuccess(event);
            case FINISH_FAIL -> sagaResolver.finishFail(event);
        }
    }
}
