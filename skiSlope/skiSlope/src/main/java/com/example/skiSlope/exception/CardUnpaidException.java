package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class CardUnpaidException extends BusinessException {
    public CardUnpaidException(){
        super(HttpStatus.BAD_REQUEST.value(), "Card unpaid!");
    }
}
