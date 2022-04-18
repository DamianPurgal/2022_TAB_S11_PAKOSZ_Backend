package com.example.skiSlope.repository;

import com.example.skiSlope.model.TicketOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("ticket_options")
public interface TicketOptionRepository  extends CrudRepository<TicketOption, Long>,
        PagingAndSortingRepository<TicketOption, Long>,
        JpaRepository<TicketOption, Long> {
}