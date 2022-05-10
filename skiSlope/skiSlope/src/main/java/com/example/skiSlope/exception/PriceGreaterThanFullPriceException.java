package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class PriceGreaterThanFullPriceException extends  BusinessException{
    public PriceGreaterThanFullPriceException() {
        super(HttpStatus.BAD_REQUEST.value(), "Price cannot be greater than FullPrice without discount!");
    }
}
