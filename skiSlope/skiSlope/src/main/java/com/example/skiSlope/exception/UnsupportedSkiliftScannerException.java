package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class UnsupportedSkiliftScannerException extends BusinessException {
    public UnsupportedSkiliftScannerException(){
        super(HttpStatus.BAD_REQUEST.value(), "Scanner doesnt accept this skilift!");
    }
}
