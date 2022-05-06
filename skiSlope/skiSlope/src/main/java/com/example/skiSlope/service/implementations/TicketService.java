package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.TicketNotFoundException;
import com.example.skiSlope.exception.VoucherNotFoundException;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.model.request.TicketRequest;
import com.example.skiSlope.model.request.TicketUpdateRequest;
import com.example.skiSlope.repository.TicketRepository;
import com.example.skiSlope.service.definitions.TicketServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> getAllTicketsByUserId(Long userId) {
        return ticketRepository.findAllByUserId(userId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public void updateTicketsData(TicketUpdateRequest ticketUpdateRequest, Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(TicketNotFoundException::new);
        ticket = ticketUpdateRequest.updateTicket(ticket);
        System.out.println(ticket.getOwnerName());
        ticketRepository.save(ticket);
//        System.out.println("Voucher saved");
//
//        Ticket ticket = ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
//        ticket = newTicket;
//        ticket.setId(id);
//        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

}
