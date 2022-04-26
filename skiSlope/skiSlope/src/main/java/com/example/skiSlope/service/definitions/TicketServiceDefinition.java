package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.request.TicketRequest;

import java.util.List;
import java.util.Optional;

public interface TicketServiceDefinition {

    Ticket addTicket(Ticket Ticket);

    Optional<Ticket> getTicketById(Long id);

    List<Ticket> getAllTicketsByUserId(Long userId);

    List<Ticket> getAllTickets();

    void updateTicketsData(TicketRequest ticketRequest, Long id);

    void deleteTicket(Long id);
}
