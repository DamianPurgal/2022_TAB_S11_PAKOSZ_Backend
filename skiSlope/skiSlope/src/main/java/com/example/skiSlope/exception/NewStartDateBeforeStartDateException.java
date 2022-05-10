package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class NewStartDateBeforeStartDateException extends BusinessException{
    public NewStartDateBeforeStartDateException() {
        super(HttpStatus.BAD_REQUEST.value(), "Start day with later or the same date already exists! You cannot add this record");
    }
}
