package com.example.skiSlope.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

     protected final int status;
     protected final String message;

    public BusinessException(int status, String message){
        super(message);
        this.message = message;
        this.status = status;
    }
}
