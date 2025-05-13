package com.example.orchestrator.service.infrastructure.saga.usecases;

import com.example.orchestrator.service.infrastructure.dto.event.EventDTO;

public interface SagaResolver {

    void onStart(EventDTO event) throws Exception;

    void onContinue(EventDTO event) throws Exception;

    void finishSuccess(EventDTO event) throws Exception;

    void finishFail(EventDTO event) throws Exception;
}
