package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class PriceNotFoundException extends BusinessException{

    public PriceNotFoundException() {super(HttpStatus.NOT_FOUND.value(), "Given price option doesn't exist");}
}
