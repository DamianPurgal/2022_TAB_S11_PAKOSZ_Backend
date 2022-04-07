package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class JwtExpiredException extends BusinessException {

    public JwtExpiredException(){
        super(HttpStatus.FORBIDDEN.value(), "JWT expired");
    }
}
