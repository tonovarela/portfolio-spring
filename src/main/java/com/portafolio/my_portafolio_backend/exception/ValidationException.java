package com.portafolio.my_portafolio_backend.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class ValidationException extends RuntimeException {
    private final BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        super("Validation failed: " + bindingResult.getErrorCount());
        this.bindingResult = bindingResult;
    }
}
