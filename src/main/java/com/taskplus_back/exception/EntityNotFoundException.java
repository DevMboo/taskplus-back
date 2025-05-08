package com.taskplus_back.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApiException {
    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, "entity_not_found");
    }
}