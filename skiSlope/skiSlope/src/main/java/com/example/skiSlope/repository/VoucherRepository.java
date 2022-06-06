package com.example.skiSlope.repository;

import com.example.skiSlope.model.Payment;
import com.example.skiSlope.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("vouchers")
public interface VoucherRepository extends CrudRepository<Voucher, Long>,
        JpaRepository<Voucher, Long>,
        PagingAndSortingRepository<Voucher, Long> {

    List<Voucher> findAllByUserId(Long userId);

    Optional<Voucher> findByCode(UUID code);

    List<Voucher> findAllByPaymentId(Long paymentId);
//    void deleteAllByPayment(Payment payment);
}
