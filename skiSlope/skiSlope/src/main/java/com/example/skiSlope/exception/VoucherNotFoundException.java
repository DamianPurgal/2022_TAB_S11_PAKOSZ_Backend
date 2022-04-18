package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class VoucherNotFoundException extends ResourceNotFoundException {
    public VoucherNotFoundException() {
        super("Voucher doesn't exist!", HttpStatus.NOT_FOUND.value());
    }
}
