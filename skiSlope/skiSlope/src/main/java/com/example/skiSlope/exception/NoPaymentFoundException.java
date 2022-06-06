package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.example.skiSlope.model.enums.EntriesEnum.allEntryEnumValues;

public class NoPaymentFoundException extends  BusinessException{
    public NoPaymentFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Payment for given id doesnt exist.");
    }
}

