package com.example.report_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //   // Validation hataları için handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error :ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());
        }

        ErrorDetails errorDetails = ErrorDetails.builder()
                .dateTime(LocalDateTime.now())
                .message("Validation Failed")
                .description(request.getDescription(false))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .validationErrors(errors)
                .build();

        return new ResponseEntity<>(errorDetails,  HttpStatus.BAD_REQUEST);
    }
}
