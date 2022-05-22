package com.example.skiSlope.repository;

import com.example.skiSlope.model.Scan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanRepository extends CrudRepository<Scan, Long> {
}
