package com.example.skiSlope.repository;

import com.example.skiSlope.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
//attempt to connect to MySQL
@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {


}
