package com.example.skiSlope.exception;

import com.example.skiSlope.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExpireDateEarlierThanStartDateException extends BusinessException {
    public ExpireDateEarlierThanStartDateException(){
        super(HttpStatus.BAD_REQUEST.value(), "ExpireDate occurs before startDate!");
    }
}
