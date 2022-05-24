package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class ScanNotFoundException extends BusinessException{

    public ScanNotFoundException(){
        super(HttpStatus.NOT_FOUND.value(), "Scan not found");
    }
}
