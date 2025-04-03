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

import static com.product.validation.service.infrastructure.shared.constants.SagaStatus.*;

@Slf4j
public class SendProduct {

    private final SaveProductValidation save;
    private final UpdateProductValidation update;
    private final GetExistsProductValidationByOrderAndTransaction getProductValidation;
    private final JsonSerializer serializer;
    private final ProducerTopic producer;
    private static final String CURRENT_SOURCE = "PRODUCT_VALIDATION_SERVICE";

    public SendProduct(SaveProductValidation save, UpdateProductValidation update, GetExistsProductValidationByOrderAndTransaction getProductValidation, JsonSerializer serializer, ProducerTopic producer) {
        this.save = save;
        this.update = update;
        this.getProductValidation = getProductValidation;
        this.serializer = serializer;
        this.producer = producer;
    }

    public void success(EventDTO dto) throws JsonProcessingException {

        try {

            save.execute(Event.from(dto), true);

            dto.setStatus(String.valueOf(SUCCESS));
            dto.setSource(CURRENT_SOURCE);
            dto.addHistory("Products are validated successfully!");

        } catch (Exception e) {

            log.error("Error trying to validate product: ", e);

            dto.setStatus(String.valueOf(ROLLBACK_PENDING));
            dto.setSource(CURRENT_SOURCE);

            dto.addHistory("Fail to validate products: ".concat(e.getMessage()));
        }

        producer.send(serializer.toJson(dto));
    }


    public void rollback(EventDTO dto) throws JsonProcessingException {

        dto.setStatus(String.valueOf(FAIL));
        dto.setSource(CURRENT_SOURCE);
        dto.addHistory("Rollback executed on product validation!");

        if (getProductValidation.execute(dto.getOrderId(), dto.getTransactionId()))
            update.execute(Event.from(dto), false);
        else
            save.execute(Event.from(dto), false);

        producer.send(serializer.toJson(dto));
    }
}
