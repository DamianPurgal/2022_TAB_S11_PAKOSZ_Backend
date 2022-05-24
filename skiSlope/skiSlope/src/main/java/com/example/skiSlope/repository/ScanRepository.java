package com.example.skiSlope.repository;

import com.example.skiSlope.model.Scan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScanRepository extends CrudRepository<Scan, Long> {

    List<Scan> findAll();

    Page<Scan> findAll(Pageable pageable);
}
