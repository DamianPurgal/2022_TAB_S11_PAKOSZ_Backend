package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class VoucherNotFoundException extends BusinessException {
    public VoucherNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Voucher doesn't exist!");
    }
}
