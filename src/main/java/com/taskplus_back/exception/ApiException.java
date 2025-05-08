package com.taskplus_back.exception;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public abstract class ApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final Map<String, Object> details;
    private final LocalDateTime timestamp;

    public ApiException(String message, HttpStatus httpStatus, String errorCode) {
        this(message, httpStatus, errorCode, null);
    }

    public ApiException(String message, HttpStatus httpStatus, String errorCode, Map<String, Object> details) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}