package com.example.skiSlope.api;


import com.example.skiSlope.model.ScannerQR;
import com.example.skiSlope.model.request.ScannerQRAddRequest;
import com.example.skiSlope.model.request.ScannerQREditRequest;
import com.example.skiSlope.model.request.ScannerQRLoginRequest;
import com.example.skiSlope.model.response.JwtTokensResponse;
import com.example.skiSlope.model.response.ScannerQRDetailedInformationResponse;
import com.example.skiSlope.model.response.ScannerQRInformationResponse;
import com.example.skiSlope.service.definitions.ScannerQRServiceDefinition;
import com.example.skiSlope.service.implementations.SkiLiftService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


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

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @PostMapping
    public void addScanner(@Valid @NonNull @RequestBody ScannerQRAddRequest scannerQRAddRequest) {
        ScannerQR scannerQR = scannerQRAddRequest.ScannerAddRequestToScanner();
        scannerQR.setSkiLift(skiLiftService.getSkyLiftById(scannerQRAddRequest.getSkiLiftId()));
        scannerQRService.addScanner(scannerQR);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteScanner(@PathVariable("id") Long scannerId){
        scannerQRService.deleteScanner(scannerId);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
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
    @GetMapping
    public ScannerQRInformationResponse getScannerInfo(){
        ScannerQR scannerQR =  (ScannerQR) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        scannerQR = scannerQRService.getById(scannerQR.getId());

        return new ScannerQRInformationResponse(
                scannerQR.getSkiLift().getId(),
                scannerQR.getTitle()
        );
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @GetMapping("/getAll")
    public List<ScannerQRDetailedInformationResponse> getAllScanners(){
        return scannerQRService.getAll()
                .stream()
                .map(
                        scanner -> ScannerQRDetailedInformationResponse.builder()
                                .id(scanner.getId())
                                .login(scanner.getLogin())
                                .title(scanner.getTitle())
                                .skiLiftId(scanner.getSkiLift().getId())
                                .build()
                    )
                .collect(Collectors.toList());
    }

}
