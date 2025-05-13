package com.product.validation.service.infrastructure.rest.api.product;


import com.product.validation.service.core.domain.event.Event;
import com.product.validation.service.core.usecases.validation.GetExistsProductValidationByOrderAndTransaction;
import com.product.validation.service.core.usecases.validation.SaveProductValidation;
import com.product.validation.service.core.usecases.validation.UpdateProductValidation;
import com.product.validation.service.infrastructure.rest.api.publisher.EventPublisher;
import lombok.extern.slf4j.Slf4j;

import static com.product.validation.service.infrastructure.shared.constants.CommonConstants.CURRENT_SOURCE;
import static com.product.validation.service.infrastructure.shared.constants.Status.*;

@Slf4j
public class ProductResolver {

    private final SaveProductValidation save;
    private final UpdateProductValidation update;
    private final GetExistsProductValidationByOrderAndTransaction existsCheck;
    private final EventPublisher publisher;

    public ProductResolver(SaveProductValidation save, UpdateProductValidation update, GetExistsProductValidationByOrderAndTransaction existsCheck, EventPublisher publisher) {
        this.save = save;
        this.update = update;
        this.existsCheck = existsCheck;
        this.publisher = publisher;
    }

    public void success(Event event) throws Exception {

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

        publisher.publish(event);
    }

    public void rollback(Event event) throws Exception {

        event.setSource(CURRENT_SOURCE);
        event.setStatus(String.valueOf(FAIL));
        event.addHistory("Rollback executed on product validation!");

        if (existsCheck.execute(event.getOrderId(), event.getTransactionId()))
            update.execute(event, false);
        else
            save.execute(event, false);

        publisher.publish(event);
    }

    private void setStatus(){

    }
}
