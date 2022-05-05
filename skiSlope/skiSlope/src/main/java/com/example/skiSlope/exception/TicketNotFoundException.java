package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class TicketNotFoundException extends BusinessException {

    public TicketNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "Ticket doesn't exist");
    }
}
