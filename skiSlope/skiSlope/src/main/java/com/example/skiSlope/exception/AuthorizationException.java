package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends BusinessException{

    public AuthorizationException(){
        super(HttpStatus.FORBIDDEN.value(), "Authorization failure");
    }
}
