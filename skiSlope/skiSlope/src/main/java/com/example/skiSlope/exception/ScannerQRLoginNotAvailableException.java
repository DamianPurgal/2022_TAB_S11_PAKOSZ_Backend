package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class ScannerQRLoginNotAvailableException extends BusinessException{

    public ScannerQRLoginNotAvailableException() { super(HttpStatus.BAD_REQUEST.value(), "Login is not available"); }
}
