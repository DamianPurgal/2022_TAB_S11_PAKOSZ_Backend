package com.example.skiSlope.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScannerQRDetailedInformationResponse {

    private Long id;

    private String login;

    private String title;

    private Long skiLiftId;
}
