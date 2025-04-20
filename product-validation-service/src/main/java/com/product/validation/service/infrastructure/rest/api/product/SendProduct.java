package com.product.validation.service.infrastructure.rest.api.product;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.product.validation.service.core.domain.Event;
import com.product.validation.service.core.usecases.validation.GetExistsProductValidationByOrderAndTransaction;
import com.product.validation.service.core.usecases.validation.SaveProductValidation;
import com.product.validation.service.core.usecases.validation.UpdateProductValidation;
import com.product.validation.service.infrastructure.dto.event.EventDTO;
import com.product.validation.service.infrastructure.rest.api.producer.ProducerTopic;
import com.product.validation.service.infrastructure.shared.JsonSerializer;
import lombok.extern.slf4j.Slf4j;

import static com.product.validation.service.infrastructure.shared.constants.CommonConstants.CURRENT_SOURCE;
import static com.product.validation.service.infrastructure.shared.constants.SagaStatus.*;

@Slf4j
public class SendProduct {

    private final SaveProductValidation save;
    private final UpdateProductValidation update;
    private final GetExistsProductValidationByOrderAndTransaction getProductValidation;
    private final JsonSerializer serializer;
    private final ProducerTopic producer;

    public SendProduct(SaveProductValidation save, UpdateProductValidation update, GetExistsProductValidationByOrderAndTransaction getProductValidation, JsonSerializer serializer, ProducerTopic producer) {
        this.save = save;
        this.update = update;
        this.getProductValidation = getProductValidation;
        this.serializer = serializer;
        this.producer = producer;
    }

    public void success(EventDTO dto) throws JsonProcessingException {
        var event = Event.fromDomain(dto);
        event.setSource(CURRENT_SOURCE);

        try {
            save.execute(event, true);

            event.setStatus(String.valueOf(SUCCESS));
            event.addHistory("Products are validated successfully!");

        } catch (Exception e) {

            log.error("Error trying to validate product: ", e);

            event.setStatus(String.valueOf(ROLLBACK_PENDING));
            event.addHistory("Fail to validate products: ".concat(e.getMessage()));
        }

        producer.send(serializer.toJson(dto));
    }

    public void rollback(EventDTO dto) throws Exception {

        var event = Event.fromDomain(dto);

        event.setSource(CURRENT_SOURCE);
        event.setStatus(String.valueOf(FAIL));
        event.addHistory("Rollback executed on product validation!");

        if (getProductValidation.execute(dto.getOrderId(), dto.getTransactionId()))
            update.execute(event, false);
        else
            save.execute(event, false);

        producer.send(serializer.toJson(event));
    }
}
