package com.example.skiSlope.model.request;

import com.example.skiSlope.model.ScannerQR;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class ScannerQREditRequest {

    @NotBlank
    private Long scannerId;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NumberFormat
    private Long skiLiftId;

    private String title;


}
