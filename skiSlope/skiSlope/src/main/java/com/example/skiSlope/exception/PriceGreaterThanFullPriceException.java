package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class PriceGreaterThanFullPriceException extends  BusinessException{
    public PriceGreaterThanFullPriceException() {
        super(HttpStatus.NOT_ACCEPTABLE.value(), "Price cannot be greater than FullPrice without discount!");
    }
}
