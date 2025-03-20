package com.order.service.infrastructure.shared;


public interface RestConverter<R, E> {

    default E mapToEntity(R rest) {
        throw new UnsupportedOperationException();
    }

    default R mapToRest(E entity) {
        throw new UnsupportedOperationException();
    }

}
