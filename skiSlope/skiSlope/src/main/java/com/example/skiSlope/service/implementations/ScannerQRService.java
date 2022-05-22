package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.ScannerQRFailedToLoginException;
import com.example.skiSlope.exception.ScannerQRLoginNotAvailableException;
import com.example.skiSlope.exception.ScannerQRNotFoundException;
import com.example.skiSlope.model.ScannerQR;
import com.example.skiSlope.model.request.ScannerQRLoginRequest;
import com.example.skiSlope.model.response.JwtTokensResponse;
import com.example.skiSlope.repository.ScannerQRRepository;
import com.example.skiSlope.security.utility.JwtGenerator;
import com.example.skiSlope.service.definitions.ScannerQRServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Service
public class ScannerQRService implements ScannerQRServiceDefinition {

    @Autowired
    private ScannerQRRepository scannerQRRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ScannerQR addScanner(ScannerQR scannerQR) {
        if(scannerQRRepository.existsByLogin(scannerQR.getLogin())){
            throw new ScannerQRLoginNotAvailableException();
        }
        scannerQR.setPassword(passwordEncoder.encode(scannerQR.getPassword()));
        return scannerQRRepository.save(scannerQR);
    }

    @Override
    public ScannerQR updateScanner(ScannerQR scannerQR){
        return scannerQRRepository.save(scannerQR);
    }

    @Override
    public ScannerQR findById(Long id){
        return scannerQRRepository.findById(id).orElseThrow(ScannerQRNotFoundException::new);
    }

    @Override
    public void deleteScanner(Long id) {
        scannerQRRepository.deleteById(id);
    }

    @Override
    public JwtTokensResponse loginAsScanner(ScannerQRLoginRequest loginRequest) {
        ScannerQR scanner = scannerQRRepository.findByLogin(loginRequest.getLogin())
                .orElseThrow(ScannerQRNotFoundException::new);
        if(!passwordEncoder.matches(loginRequest.getPassword(), scanner.getPassword())){
            throw new ScannerQRFailedToLoginException();
        }

        return new JwtTokensResponse(
                JwtGenerator.generateJWTAccessTokenForScanner(scanner, "scannerLogin"),
                JwtGenerator.generateJWTRefreshTokenForScanner(scanner, "scannerLogin")
        );
    }
}
