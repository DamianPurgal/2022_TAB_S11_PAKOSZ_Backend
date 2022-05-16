package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.request.TicketUpdateRequest;

import java.util.List;

public interface TicketServiceDefinition {

    Ticket addTicket(Ticket Ticket);

    Ticket getTicketById(Long id);

    List<Ticket> getAllTicketsByUserId(Long userId);

    List<Ticket> getAllTicketsBySkiLiftId(Long skiLiftId);

    List<Ticket> getAllTickets();

    void updateTicketsData(TicketUpdateRequest ticketUpdateRequest, Long id);

    void deleteTicket(Long id);
}
