package com.example.skiSlope.exception;

import com.example.skiSlope.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExpireDateEarlierThanStartDateException extends BusinessException {
    public ExpireDateEarlierThanStartDateException(){
        super(HttpStatus.NOT_ACCEPTABLE.value(), "ExpireDate occurs before startDate!");
    }
}
