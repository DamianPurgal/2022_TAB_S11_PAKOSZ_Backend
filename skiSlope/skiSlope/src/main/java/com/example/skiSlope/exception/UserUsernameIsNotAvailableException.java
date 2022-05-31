package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class UserUsernameIsNotAvailableException extends BusinessException{

    public UserUsernameIsNotAvailableException(){
        super(HttpStatus.CONFLICT.value(), "Username is not available");
    }
}
