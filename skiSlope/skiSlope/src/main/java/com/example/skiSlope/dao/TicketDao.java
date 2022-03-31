package com.example.skiSlope.dao;

import com.example.skiSlope.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketDao {

    int insertTicket(UUID code, Ticket ticket);

    default int insertTicket(Ticket ticket){
        UUID code = UUID.randomUUID();
        return insertTicket(code, ticket);
    }

    List<Ticket> selectAllTickets();

    Optional<Ticket> selectTicketByCode(UUID code);

    int deleteTicketByCode(UUID code);
    int updateTicketByCode(UUID code, Ticket ticket);
}
