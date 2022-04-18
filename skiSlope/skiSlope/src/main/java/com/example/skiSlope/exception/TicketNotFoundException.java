package com.example.skiSlope.exception;

import org.springframework.http.HttpStatus;

public class TicketNotFoundException extends ResourceNotFoundException {

    public TicketNotFoundException() {
        super("Ticket doesn't exist", HttpStatus.NOT_FOUND.value());
    }
}
