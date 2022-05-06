package com.example.skiSlope.repository;

import com.example.skiSlope.model.Price;
import com.example.skiSlope.model.VoucherOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository("voucher_options")
public interface VoucherOptionRepository  extends CrudRepository<VoucherOption, Long>,
        PagingAndSortingRepository<VoucherOption, Long>,
        JpaRepository<VoucherOption, Long> {

    List<VoucherOption> findVoucherOptionsByStartDate(Date startDate);

    List<VoucherOption> findVoucherOptionsByExpireDate(Date expireDate);
}