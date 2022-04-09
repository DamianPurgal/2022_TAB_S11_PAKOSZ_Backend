package com.example.skiSlope.datasource.exception;

import lombok.Getter;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException {

    protected final int status;
    protected final String message;

    public ResourceNotFoundException(String message, int status){
        super(message);
        this.message = message;
        this.status = status;
    }
}
