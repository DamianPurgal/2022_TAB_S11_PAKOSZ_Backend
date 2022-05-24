package com.example.skiSlope.repository;

import com.example.skiSlope.model.ScannerQR;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScannerQRRepository extends CrudRepository<ScannerQR, Long> {
    boolean existsByLogin(String login);

    Optional<ScannerQR> findById(Long id);

    void deleteById(Long id);

    Optional<ScannerQR> findByLogin(String login);

    List<ScannerQR> findAll();

}
