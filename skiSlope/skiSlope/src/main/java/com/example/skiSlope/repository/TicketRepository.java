package com.example.skiSlope.repository;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Ticket;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository("tickets")
public interface TicketRepository extends CrudRepository<Ticket, Long>,
        PagingAndSortingRepository<Ticket, Long>,
        JpaRepository<Ticket, Long> {

    List<Ticket> findAllByUserId(Long userId);



}
