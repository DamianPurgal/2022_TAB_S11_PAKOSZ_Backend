package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class CardNotFoundException extends BusinessException {
    public CardNotFoundException(){
        super(HttpStatus.BAD_REQUEST.value(), "Card not found!");
    }
}
