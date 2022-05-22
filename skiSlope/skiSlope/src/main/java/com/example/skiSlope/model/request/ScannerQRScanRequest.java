package com.example.skiSlope.model.request;

import lombok.*;
import org.springframework.lang.NonNull;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScannerQRScanRequest {

    @NonNull
    private UUID code;
}
