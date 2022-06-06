package com.example.skiSlope.repository;

import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.EntriesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository("ticket_options")
public interface TicketOptionRepository  extends CrudRepository<TicketOption, Long>,
        PagingAndSortingRepository<TicketOption, Long>,
        JpaRepository<TicketOption, Long> {

    List<TicketOption> findAllByExpireDateEquals(Date expireDate);

    List<TicketOption> findAllByExpireDateGreaterThanEqualAndStartDateLessThanEqual(Date timeNow, Date timeNow2);

    Optional<TicketOption> findByExpireDateGreaterThanEqualAndStartDateLessThanEqualAndId(Date timeNow, Date timeNow2, Long id);

    Optional<TicketOption> findByExpireDateGreaterThanEqualAndStartDateLessThanEqualAndDiscountTypeEqualsAndEntriesEquals(Date timeNow, Date timeNow2, DiscountType discountType, int entries);


}