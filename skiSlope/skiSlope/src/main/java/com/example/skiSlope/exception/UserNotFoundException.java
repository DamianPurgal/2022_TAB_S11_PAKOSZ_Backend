package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException{

    public UserNotFoundException(){
        super(HttpStatus.NOT_FOUND.value(), "User not found");
    }
}
