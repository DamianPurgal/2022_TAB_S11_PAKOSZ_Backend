package com.example.skiSlope.service.definitions;


import com.example.skiSlope.model.ScannerQR;
import com.example.skiSlope.model.request.ScannerQRLoginRequest;
import com.example.skiSlope.model.response.JwtTokensResponse;

public interface ScannerQRServiceDefinition {

    ScannerQR addScanner(ScannerQR scannerQR);

    ScannerQR updateScanner(ScannerQR scannerQR);

    ScannerQR findById(Long id);

    void deleteScanner(Long id);

    JwtTokensResponse loginAsScanner(ScannerQRLoginRequest loginRequest);
}
