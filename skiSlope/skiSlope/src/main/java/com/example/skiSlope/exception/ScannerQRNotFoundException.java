package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class ScannerQRNotFoundException extends BusinessException{

    public ScannerQRNotFoundException(){
        super(HttpStatus.NOT_FOUND.value(), "Scanner not found");
    }
}
