package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class UserPasswordsDoesntMatchException extends BusinessException{

    public UserPasswordsDoesntMatchException(){
        super(HttpStatus.FORBIDDEN.value(), "Passwords doesnt match");
    }
}
