package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class UserUsernameIsNotAvailableException extends BusinessException{

    public UserUsernameIsNotAvailableException(){
        super(HttpStatus.BAD_REQUEST.value(), "Username is not available");
    }
}
