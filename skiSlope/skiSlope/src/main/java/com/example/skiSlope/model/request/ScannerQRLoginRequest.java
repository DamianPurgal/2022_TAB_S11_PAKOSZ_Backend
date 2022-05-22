package com.example.skiSlope.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class ScannerQRLoginRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
