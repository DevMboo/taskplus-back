package com.taskplus_back.exception;

import com.taskplus_back.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorResponseDTO> handleApiException(
          ApiException ex,
          HttpServletRequest request) {

    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
            .timestamp(ex.getTimestamp())
            .status(ex.getHttpStatus().value())
            .error(ex.getHttpStatus().getReasonPhrase())
            .code(ex.getErrorCode())
            .message(ex.getMessage())
            .details(ex.getDetails() != null ? ex.getDetails() : Collections.emptyMap())
            .path(request.getRequestURI())
            .build();

    return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGenericException(
          Exception ex,
          HttpServletRequest request) {

    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .code("internal_server_error")
            .message("Ocorreu um erro inesperado no servidor -> " + ex.getMessage() + " Linha -> " + ex.getClass())
            .path(request.getRequestURI())
            .build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}