package com.example.skiSlope.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String message) {
        super(HttpStatus.NO_CONTENT.value(), message);
    }
}
