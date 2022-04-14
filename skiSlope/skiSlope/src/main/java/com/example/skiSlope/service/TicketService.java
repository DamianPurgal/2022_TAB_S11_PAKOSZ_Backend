package com.example.skiSlope.service;

import com.example.skiSlope.datasource.exception.TicketNotFoundException;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TicketService implements TicketServiceDefinition {

    private TicketRepository ticketRepository;

    @Override
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> getAllTicketsByUserId(Long userId) {
        return ticketRepository.findAllById(Collections.singleton(userId));
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void updateTicketsData(Ticket newTicket, Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        ticket = newTicket;
        ticket.setId(id);
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

}
