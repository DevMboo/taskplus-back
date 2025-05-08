package com.taskplus_back.exception;

import org.springframework.http.HttpStatus;
import java.util.Map;

public class BusinessException extends ApiException {
    public BusinessException(String message) {
        super(message, HttpStatus.BAD_REQUEST, "business_error");
    }

    public BusinessException(String message, Map<String, Object> details) {
        super(message, HttpStatus.BAD_REQUEST, "business_error", details);
    }
}