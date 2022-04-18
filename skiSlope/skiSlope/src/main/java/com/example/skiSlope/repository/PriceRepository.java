package com.example.skiSlope.repository;

import com.example.skiSlope.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("prices")
public interface PriceRepository  extends CrudRepository<Price, Long>,
        PagingAndSortingRepository<Price, Long>,
        JpaRepository<Price, Long> {

    List<Price> findPricesByStartDate(Date startDate);

    List<Price> findPricesByExpireDate(Date expireDate);
}
