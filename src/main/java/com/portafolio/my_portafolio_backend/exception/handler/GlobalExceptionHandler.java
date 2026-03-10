package com.portafolio.my_portafolio_backend.exception.handler;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model) {
         model.addAttribute("errors",ex.getBindingResult().getAllErrors() );
         model.addAttribute("message", "Se encontraron errores de validación. Por favor, revise los datos ingresados.");
        return "error/validation";

    }
}
