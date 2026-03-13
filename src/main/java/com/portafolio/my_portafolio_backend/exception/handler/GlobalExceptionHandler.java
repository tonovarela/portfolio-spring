package com.portafolio.my_portafolio_backend.exception.handler;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import com.portafolio.my_portafolio_backend.clases.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        List<String> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::formatFieldError)
                .toList();

        return new ResponseEntity<>(
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Error de validación",
                        errors,
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex
                .getFieldErrors()
                .stream()
                .map(this::formatFieldError)
                .toList();

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Datos inválidos",
                        errors,
                        LocalDateTime.now()
                ));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Error interno del servidor",
                        List.of(nonNullMessage(ex.getMessage(), "Error no especificado")),
                        LocalDateTime.now()
                ));
    }


    private String formatFieldError(FieldError error) {
        return error.getField() + ": " + nonNullMessage(error.getDefaultMessage(), "Valor inválido");
    }

    private String nonNullMessage(String message, String fallback) {
        if (message == null || message.isBlank()) {
            return fallback;
        }
        return message;
    }
}
