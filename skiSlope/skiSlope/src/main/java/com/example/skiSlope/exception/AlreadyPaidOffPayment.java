package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class AlreadyPaidOffPayment extends BusinessException{
    public AlreadyPaidOffPayment() {
        super(HttpStatus.BAD_REQUEST.value(), "Payment was already paid off");
    }
}
