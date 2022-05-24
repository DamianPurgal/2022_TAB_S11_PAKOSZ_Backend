package com.example.skiSlope.api;

import com.example.skiSlope.model.ScannerQR;
import com.example.skiSlope.model.request.ScannerQRScanRequest;
import com.example.skiSlope.service.definitions.ScanServiceDefinition;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RequestMapping("api/scan")
@RestController
public class ScanController {

    ScanServiceDefinition scanServiceDefinition;

    @PreAuthorize("hasAnyRole('ROLE_SCANNER')")
    @PostMapping
    public void scanCard(@Valid @NonNull @RequestBody ScannerQRScanRequest scannerQRScanRequest) {
        ScannerQR scannerQR =  (ScannerQR) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        scanServiceDefinition.addScan(scannerQR.getId(), scannerQRScanRequest.getCode());
    }

}
