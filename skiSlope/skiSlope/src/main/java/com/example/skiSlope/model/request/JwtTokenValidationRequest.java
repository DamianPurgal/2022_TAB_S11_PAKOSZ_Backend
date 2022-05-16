package com.example.skiSlope.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JwtTokenValidationRequest {

    @NotBlank
    String token;
}
