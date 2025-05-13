package com.example.orchestrator.service.infrastructure.config.logs;

import com.example.orchestrator.service.infrastructure.dto.event.EventDTO;
import com.example.orchestrator.service.infrastructure.shared.constants.Topic;
import lombok.extern.slf4j.Slf4j;

import static java.lang.String.format;

@Slf4j
public class TopicLogger {

    private static final String SAGA_LOG_ID = "ORDER ID: %s | TRANSACTION ID %s | EVENT ID %s";

    public void log(EventDTO event, Topic topic) {
        var sagaId = createSagaId(event);
        var source = event.getSource();

        switch (event.getStatus()) {

            case SUCCESS -> log.info("SAGA: {} | SUCCESS | NEXT TOPIC {} | {}", source, topic, sagaId);

            case ROLLBACK_PENDING ->
                    log.info("SAGA: {} | ROLLBACK CURRENT SERVICE | NEXT TOPIC {} | {}", source, topic, sagaId);

            case FAIL -> log.info("SAGA: {} | ROLLBACK PREVIOUS SERVICE | NEXT TOPIC {} | {}", source, topic, sagaId);

            default -> log.warn("SAGA: {} | UNKNOWN STATUS {} | {}", source, event.getStatus(), sagaId);
        }
    }

    private String createSagaId(EventDTO event) {
        return format(SAGA_LOG_ID, event.getOrder().getId(), event.getTransactionId(), event.getId());
    }
}
