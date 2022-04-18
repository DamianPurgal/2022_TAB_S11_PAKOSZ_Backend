package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class PriceNotFoundException extends ResourceNotFoundException{

    public PriceNotFoundException() {super("Given price option doesn't exist", HttpStatus.NOT_FOUND.value());}
}
