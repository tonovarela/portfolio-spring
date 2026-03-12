package com.portafolio.my_portafolio_backend.clases;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {
  private   int status;
  private   String message;
  private   List<String> errors;
  private   LocalDateTime timestamp;
}


