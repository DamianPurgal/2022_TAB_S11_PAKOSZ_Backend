package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class SkiLiftNotFoundException extends BusinessException{
    public SkiLiftNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Ski lift for given id not found!");
    }
}
