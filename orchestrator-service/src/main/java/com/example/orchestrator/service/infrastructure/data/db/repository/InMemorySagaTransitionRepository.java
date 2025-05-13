package com.example.orchestrator.service.infrastructure.data.db.repository;

import com.example.orchestrator.service.core.domain.SagaTransition;
import com.example.orchestrator.service.core.ports.SagaTransitionRepositoryPort;
import com.example.orchestrator.service.infrastructure.shared.constants.Source;
import com.example.orchestrator.service.infrastructure.shared.constants.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.orchestrator.service.infrastructure.shared.constants.Source.*;
import static com.example.orchestrator.service.infrastructure.shared.constants.Status.*;
import static com.example.orchestrator.service.infrastructure.shared.constants.Topic.*;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class InMemorySagaTransitionRepository implements SagaTransitionRepositoryPort {

    private final List<SagaTransition> transitions = List.of(
            new SagaTransition(ORCHESTRATOR, SUCCESS, PRODUCT_VALIDATION_SUCCESS),
            new SagaTransition(ORCHESTRATOR, FAIL, FINISH_FAIL),

            new SagaTransition(PRODUCT_VALIDATION_SERVICE, ROLLBACK_PENDING, PRODUCT_VALIDATION_FAIL),
            new SagaTransition(PRODUCT_VALIDATION_SERVICE, FAIL, FINISH_FAIL),
            new SagaTransition(PRODUCT_VALIDATION_SERVICE, SUCCESS, PAYMENT_SUCCESS),

            new SagaTransition(PAYMENT_SERVICE, ROLLBACK_PENDING, PAYMENT_FAIL),
            new SagaTransition(PAYMENT_SERVICE, FAIL, PRODUCT_VALIDATION_FAIL),
            new SagaTransition(PAYMENT_SERVICE, SUCCESS, INVENTORY_SUCCESS),

            new SagaTransition(INVENTORY_SERVICE, ROLLBACK_PENDING, INVENTORY_FAIL),
            new SagaTransition(INVENTORY_SERVICE, FAIL, PAYMENT_FAIL),
            new SagaTransition(INVENTORY_SERVICE, SUCCESS, FINISH_SUCCESS)
    );

    @Override
    public Optional<SagaTransition> findBy(Source source, Status status) {
        return transitions.stream()
                .filter(t -> t.matches(source, status))
                .findFirst();
    }
}
