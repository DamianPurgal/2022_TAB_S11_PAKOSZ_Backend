package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Scan;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ScanServiceDefinition {

    int SCANS_PER_PAGE = 10;

    Scan addScan(Long scannerId, UUID code);

    List<Scan> getAll();

    Page<Scan> getPage(int page);
}
