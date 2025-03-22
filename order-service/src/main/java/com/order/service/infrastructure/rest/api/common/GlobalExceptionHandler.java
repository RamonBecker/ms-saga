package com.order.service.infrastructure.rest.api.common;


import com.order.service.core.usecases.event.exception.NotFoundEventException;
import com.order.service.infrastructure.rest.api.responses.ApiResponse;
import com.order.service.infrastructure.shared.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, ex.getMessage()));
    }


    @ResponseBody
    @ExceptionHandler(NotFoundEventException.class)
    ResponseEntity<ApiResponse> handleNotFoundEventException(NotFoundEventException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, ex.getMessage()));
    }



}
