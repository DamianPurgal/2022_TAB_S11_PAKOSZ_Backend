package com.example.skiSlope.repository;

import com.example.skiSlope.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>,
        PagingAndSortingRepository<Payment, Long>,
        JpaRepository<Payment, Long> {

    List<Payment> findAllByUserId(Long userId);
}
