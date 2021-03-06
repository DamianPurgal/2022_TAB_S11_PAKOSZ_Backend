package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.User;
import com.example.skiSlope.model.request.TicketUpdateRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketServiceDefinition {

    Ticket addTicket(Ticket Ticket);

    Ticket getTicketById(Long id);

    List<Ticket> getAllTicketsByUserId(Long userId);

    List<Ticket> getAllTicketsBySkiLiftId(Long skiLiftId);

    List<Ticket> getAllTickets();

    void updateTicketsData(TicketUpdateRequest ticketUpdateRequest, Long id, User user);

    void deleteTicket(Long id);

    Optional<Ticket> getTicketByCode(UUID code);

    Ticket updateTicket(Ticket ticket);

    void deleteAllTicketsByPaymentId(Long paymentId);
}
