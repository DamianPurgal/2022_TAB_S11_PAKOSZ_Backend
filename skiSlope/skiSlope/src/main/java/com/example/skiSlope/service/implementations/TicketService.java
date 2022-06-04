package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.TicketNotFoundException;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.request.TicketUpdateRequest;
import com.example.skiSlope.repository.TicketRepository;
import com.example.skiSlope.service.definitions.TicketServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class TicketService implements TicketServiceDefinition {

    private TicketRepository ticketRepository;

    @Override
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);
    }

    @Override
    public List<Ticket> getAllTicketsByUserId(Long userId) {
        return ticketRepository.findAllByUserId(userId);
    }

    @Override
    public List<Ticket> getAllTicketsBySkiLiftId(Long skiLiftId) {
        return ticketRepository.findAllBySkiLiftId(skiLiftId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void updateTicketsData(TicketUpdateRequest ticketUpdateRequest, Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);
        ticket = ticketUpdateRequest.updateTicketOwnerName(ticket);
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Optional<Ticket> getTicketByCode(UUID code) {
        return ticketRepository.findByCode(code);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}