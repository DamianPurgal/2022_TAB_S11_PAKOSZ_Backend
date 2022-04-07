package com.example.skiSlope.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokensResponse {
    private String accessToken;
    private String refreshToken;
}
