package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class JwtValidationException extends BusinessException{

    public JwtValidationException(){
        super(HttpStatus.FORBIDDEN.value(), "JWT validation error");
    }
}
