package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class NewStartDateBeforeStartDateException extends BusinessException{
    public NewStartDateBeforeStartDateException() {
        super(HttpStatus.LOCKED.value(), "Start day with later date already exists! You cannot add this record");
    }
}
