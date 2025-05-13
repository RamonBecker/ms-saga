package com.product.validation.service.core.usecases.validation;

import com.product.validation.service.core.domain.event.Event;

public interface SaveProductValidation {

    void execute(Event event, boolean isSuccess);
}
