package com.example.skiSlope.repository;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Price;
import com.example.skiSlope.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("vouchers")
public interface VoucherRepository extends CrudRepository<Voucher, Long>,
        JpaRepository<Voucher, Long>,
        PagingAndSortingRepository<Voucher, Long> {
}
