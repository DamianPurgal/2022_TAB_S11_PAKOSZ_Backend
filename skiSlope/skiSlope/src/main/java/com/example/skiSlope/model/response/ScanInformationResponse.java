package com.example.skiSlope.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScanInformationResponse {
    private Long id;

    protected Date scanDate;

    protected Long cardId;

    protected Long skiLiftId;

    protected Long scannerQRId;
}
