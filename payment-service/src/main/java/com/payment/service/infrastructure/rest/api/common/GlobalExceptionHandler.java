package com.payment.service.infrastructure.rest.api.common;


import com.payment.service.core.usecases.payment.exception.AmountValidationException;
import com.payment.service.core.usecases.payment.exception.PaymentExistsException;
import com.payment.service.core.usecases.payment.exception.PaymentNotFoundException;
import com.payment.service.infrastructure.dto.ApiResponse;
import com.payment.service.infrastructure.shared.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse> handleValidationException(ValidationException ex) {
        return ResponseEntity.badRequest().body(new ApiResponse(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(AmountValidationException.class)
    public ResponseEntity<ApiResponse> handleAmountValidationException(AmountValidationException ex) {
        return ResponseEntity.badRequest().body(new ApiResponse(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(PaymentExistsException.class)
    public ResponseEntity<ApiResponse> handlePaymentExistsException(PaymentExistsException ex) {
        return ResponseEntity.badRequest().body(new ApiResponse(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ApiResponse> handlePaymentNotFoundException(PaymentNotFoundException ex) {
        return ResponseEntity.badRequest().body(new ApiResponse(ex.getCode(), ex.getMessage()));
    }

}
