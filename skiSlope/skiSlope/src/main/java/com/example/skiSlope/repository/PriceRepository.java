package com.example.skiSlope.repository;

import com.example.skiSlope.model.Price;
import com.example.skiSlope.model.TicketOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface PriceRepository  extends CrudRepository<Price, Long>,
        PagingAndSortingRepository<Price, Long>,
        JpaRepository<Price, Long> {

//    List<Price> findPricesByStartDate(Date startDate);
//
//    List<Price> findPricesByExpireDate(Date expireDate);

    List<Price> findAllByExpireDateGreaterThanEqualAndStartDateLessThanEqual(Date timeNow, Date timeNow2);

    Optional<Price> findByExpireDateGreaterThanEqualAndStartDateLessThanEqualAndId(Date timeNow, Date timeNow2, Long id);
}
