package com.example.skiSlope.api;

import com.example.skiSlope.exception.ScanNotFoundException;
import com.example.skiSlope.model.Scan;
import com.example.skiSlope.model.ScannerQR;
import com.example.skiSlope.model.request.ScannerQRScanRequest;
import com.example.skiSlope.model.response.ScanInformationResponse;
import com.example.skiSlope.service.definitions.ScanServiceDefinition;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @GetMapping
    public List<ScanInformationResponse> getAllScans() {
        return scanServiceDefinition.getAll().stream()
                .map(scan -> ScanInformationResponse.builder()
                        .id(scan.getId())
                        .scanDate(scan.getScanDate())
                        .cardId(scan.getCard().getId())
                        .skiLiftId(scan.getSkiLift().getId())
                        .scannerQRId(scan.getScannerQR().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @GetMapping("/page/{id}")
    public List<ScanInformationResponse> getAllScans(@NonNull @PathVariable("id") Integer id) {
        if(id < 0){
            throw new ScanNotFoundException();
        }
        return scanServiceDefinition.getPage(id).stream()
                .map(scan -> ScanInformationResponse.builder()
                        .id(scan.getId())
                        .scanDate(scan.getScanDate())
                        .cardId(scan.getCard().getId())
                        .skiLiftId(scan.getSkiLift().getId())
                        .scannerQRId(scan.getScannerQR().getId())
                        .build())
                .collect(Collectors.toList());
    }

}
