package com.product.validation.service.infrastructure.shared.exception;

public class ExceptionDetail extends Exception {

    private final int code;

    public ExceptionDetail(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
