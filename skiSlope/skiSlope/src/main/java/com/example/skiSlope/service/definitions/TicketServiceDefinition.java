package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.request.TicketRequest;
import com.example.skiSlope.model.request.TicketUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface TicketServiceDefinition {

    Ticket addTicket(Ticket Ticket);

    Optional<Ticket> getTicketById(Long id);

    List<Ticket> getAllTicketsByUserId(Long userId);

    List<Ticket> getAllTickets();

    void updateTicketsData(TicketUpdateRequest ticketUpdateRequest, Long id);

    void deleteTicket(Long id);
}
