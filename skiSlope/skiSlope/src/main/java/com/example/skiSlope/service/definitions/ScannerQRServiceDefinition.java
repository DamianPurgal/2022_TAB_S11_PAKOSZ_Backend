package com.example.skiSlope.service.definitions;


import com.example.skiSlope.model.ScannerQR;
import com.example.skiSlope.model.request.ScannerQRLoginRequest;
import com.example.skiSlope.model.response.JwtTokensResponse;

import java.util.List;

public interface ScannerQRServiceDefinition {

    ScannerQR addScanner(ScannerQR scannerQR);

    ScannerQR updateScanner(ScannerQR scannerQR);

    ScannerQR getById(Long id);

    void deleteScanner(Long id);

    JwtTokensResponse loginAsScanner(ScannerQRLoginRequest loginRequest);

    ScannerQR getByLogin(String login);

    List<ScannerQR> getAll();

}
