package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class CardInactiveException extends BusinessException {
    public CardInactiveException(){
        super(HttpStatus.BAD_REQUEST.value(), "Card is inactive!");
    }
}
