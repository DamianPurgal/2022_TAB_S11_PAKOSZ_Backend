package com.example.skiSlope.model.response;


import lombok.Data;

@Data
public class IsJwtTokenValidResponse {
    private final int status;
    private final String message;
}
