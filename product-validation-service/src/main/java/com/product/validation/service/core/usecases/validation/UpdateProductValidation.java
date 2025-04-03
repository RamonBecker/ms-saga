package com.product.validation.service.core.usecases.validation;

import com.product.validation.service.core.domain.Event;

public interface UpdateProductValidation {
    void execute(Event event, boolean isSuccess);
}
