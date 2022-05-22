package com.example.skiSlope.api;



import com.example.skiSlope.model.ScannerQR;
import com.example.skiSlope.model.request.ScannerQRAddRequest;
import com.example.skiSlope.model.request.ScannerQREditRequest;
import com.example.skiSlope.model.request.ScannerQRLoginRequest;
import com.example.skiSlope.model.request.ScannerQRScanRequest;
import com.example.skiSlope.model.response.JwtTokensResponse;
import com.example.skiSlope.service.definitions.ScannerQRServiceDefinition;
import com.example.skiSlope.service.implementations.SkiLiftService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.lang.NonNull;

import javax.validation.Valid;


@AllArgsConstructor
@RequestMapping("api/scanner")
@RestController
public class ScannerQRController {

    private ScannerQRServiceDefinition scannerQRService;
    private SkiLiftService skiLiftService;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public JwtTokensResponse loginAsScanner(@Valid @NonNull @RequestBody ScannerQRLoginRequest scannerQRLoginRequest) {
        return scannerQRService.loginAsScanner(scannerQRLoginRequest);
    }

    //manager
    @PreAuthorize("permitAll()")
    @PostMapping
    public void addScanner(@Valid @NonNull @RequestBody ScannerQRAddRequest scannerQRAddRequest) {
        ScannerQR scannerQR = scannerQRAddRequest.ScannerAddRequestToScanner();
        scannerQR.setSkiLift(skiLiftService.getSkyLiftById(scannerQRAddRequest.getSkiLiftId()));
        scannerQRService.addScanner(scannerQR);
    }

    //manager
    @PreAuthorize("permitAll()")
    @DeleteMapping("/{id}")
    public void deleteScanner(@PathVariable("id") Long scannerId){
        scannerQRService.deleteScanner(scannerId);
    }

    //manager
    @PreAuthorize("permitAll()")
    @PutMapping
    public void editScanner(@Valid @NonNull @RequestBody ScannerQREditRequest scannerQREditRequest) {
        ScannerQR scannerQR = scannerQRService.getById(scannerQREditRequest.getScannerId());
        scannerQR.setLogin(scannerQREditRequest.getLogin());
        scannerQR.setPassword(scannerQREditRequest.getPassword());
        scannerQR.setTitle(scannerQREditRequest.getTitle());
        scannerQR.setSkiLift(skiLiftService.getSkyLiftById(scannerQREditRequest.getSkiLiftId()));
        scannerQRService.updateScanner(scannerQR);
    }

    @PreAuthorize("hasAnyRole('ROLE_SCANNER')")
    @GetMapping("/test")
    public void test() {
        ScannerQR scannerQR = (ScannerQR) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
