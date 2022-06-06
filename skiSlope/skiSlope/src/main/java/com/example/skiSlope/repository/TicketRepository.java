package com.example.skiSlope.repository;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Payment;
import com.example.skiSlope.model.Ticket;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("tickets")
public interface TicketRepository extends CrudRepository<Ticket, Long>,
        PagingAndSortingRepository<Ticket, Long>,
        JpaRepository<Ticket, Long> {

    List<Ticket> findAllByUserId(Long userId);

    List<Ticket> findAllBySkiLiftId(Long skiLiftId);

    Optional<Ticket> findByCode(UUID code);

    List<Ticket> findAllByPaymentId(Long paymentId);
//    void deleteAllByPaymentId(Long paymentId);

}
