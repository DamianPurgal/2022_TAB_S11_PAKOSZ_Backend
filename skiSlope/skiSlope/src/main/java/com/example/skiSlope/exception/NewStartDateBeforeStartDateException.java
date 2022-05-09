package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class NewStartDateBeforeStartDateException extends BusinessException{
    public NewStartDateBeforeStartDateException() {
        super(HttpStatus.NOT_ACCEPTABLE.value(), "Start day with later or the same date already exists! You cannot add this record");
    }
}
