package com.taskplus_back.exception;

import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ValidationException extends ApiException {
    public ValidationException(String message, List<String> errors) {
        super(message, HttpStatus.BAD_REQUEST, "validation_error",
                Map.of("field_errors", errors));
    }

    public ValidationException(String message, Map<String, String> fieldErrors) {
        super(message, HttpStatus.BAD_REQUEST, "validation_error",
                Map.of("field_errors", fieldErrors));
    }
}