package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Scan;
import com.example.skiSlope.model.ScannerQR;

import java.util.UUID;

public interface ScanServiceDefinition {

    Scan addScan(Long scannerId, UUID code);
}
