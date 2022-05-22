package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class ScannerQRFailedToLoginException extends BusinessException {

    public ScannerQRFailedToLoginException() { super(HttpStatus.UNAUTHORIZED.value(), "Failed to login"); }
}
