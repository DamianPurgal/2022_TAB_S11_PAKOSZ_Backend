package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketServiceDefinition {

    Ticket addTicket(Ticket Ticket);

    Optional<Ticket> getTicketById(Long id);

    List<Ticket> getAllTicketsByUserId(Long userId);

    List<Ticket> getAllTickets();

    void updateTicketsData(Ticket Ticket, Long id);

    void deleteTicket(Long id);
}
