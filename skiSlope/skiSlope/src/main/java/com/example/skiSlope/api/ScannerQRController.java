package com.example.skiSlope.api;



import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.skiSlope.exception.JwtExpiredException;
import com.example.skiSlope.exception.JwtValidationException;
import com.example.skiSlope.model.ScannerQR;
import com.example.skiSlope.model.ScannerQRSkiLiftConnection;
import com.example.skiSlope.model.SkiLift;
import com.example.skiSlope.model.User;
import com.example.skiSlope.model.request.ScannerQRAddRequest;
import com.example.skiSlope.model.request.ScannerQRConnectionsAdd;
import com.example.skiSlope.model.request.ScannerQRLoginRequest;
import com.example.skiSlope.model.response.JwtTokensResponse;
import com.example.skiSlope.security.utility.JwtGenerator;
import com.example.skiSlope.security.utility.JwtResolver;
import com.example.skiSlope.service.definitions.ScannerQRServiceDefinition;
import com.example.skiSlope.service.definitions.UserService;
import com.example.skiSlope.service.implementations.SkiLiftService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


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
        scannerQRService.addScanner(scannerQR);
    }

    //manager
    @PreAuthorize("permitAll()")
    @DeleteMapping("/{id}")
    public void deleteScanner(@PathVariable("id") Long scannerId){
        scannerQRService.deleteScanner(scannerId);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/setConnections")
    public void setScannerConnections(@Valid @NonNull @RequestBody ScannerQRConnectionsAdd scannerQRConnectionsAdd) {
        ScannerQR scannerQR = scannerQRService.findById(scannerQRConnectionsAdd.getScannerId());
        List<ScannerQRSkiLiftConnection> skiLifts = new ArrayList<>();
        for(Long skiLiftId : scannerQRConnectionsAdd.getSkiLifts()){
            skiLifts.add(new ScannerQRSkiLiftConnection(null, skiLiftService.getSkyLiftById(skiLiftId), scannerQR));
        }
        //pamietaj aby nie uzywac scannerQR.setSkiLifts(skiLifts) tylko te dwie linie na dole, bo orphanRemoval nie zadziała. Trzeba uzyć clear na kolekcji.
        scannerQR.getSkiLifts().clear();
        scannerQR.getSkiLifts().addAll(skiLifts);
        scannerQRService.updateScanner(scannerQR);

    }


}
