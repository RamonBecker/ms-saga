package com.product.validation.service.infrastructure.commom;



import com.product.validation.service.infrastructure.dto.ApiResponse;
import com.product.validation.service.infrastructure.shared.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse> handleValidationException(ValidationException ex) {
        return ResponseEntity.badRequest().body(new ApiResponse(ex.getCode(), ex.getMessage()));
    }

}
