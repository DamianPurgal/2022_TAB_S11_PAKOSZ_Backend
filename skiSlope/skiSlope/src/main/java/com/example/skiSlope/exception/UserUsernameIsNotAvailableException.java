package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class UserUsernameIsNotAvailableException extends BusinessException{

    public UserUsernameIsNotAvailableException(){
        super(HttpStatus.NOT_ACCEPTABLE.value(), "Username is not available");
    }
}
