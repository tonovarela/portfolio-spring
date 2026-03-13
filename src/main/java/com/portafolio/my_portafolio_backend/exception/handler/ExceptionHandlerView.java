package com.portafolio.my_portafolio_backend.exception.handler;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class ExceptionHandlerView {

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model) {
        addValidationAttributes(model, ex.getBindingResult(), "Error de validacion en el formulario");
        return "error/validation";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, Model model) {
        addValidationAttributes(model, ex.getBindingResult(), "Datos invalidos enviados");
        return "error/validation";
    }

    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException ex, Model model) {
        addValidationAttributes(model, ex.getBindingResult(), "No se pudo procesar el formulario");
        return "error/validation";
    }

    private void addValidationAttributes(Model model, BindingResult bindingResult, String message) {
        List<String> errors = bindingResult.getFieldErrors()
                .stream()
                .map(this::formatFieldError)
                .toList();

        model.addAttribute("errors", errors);
        model.addAttribute("message", message);
    }

    private String formatFieldError(FieldError error) {
        String defaultMessage = error.getDefaultMessage();
        if (defaultMessage == null || defaultMessage.isBlank()) {
            defaultMessage = "valor invalido";
        }
        return error.getField() + ": " + defaultMessage;
    }
}
