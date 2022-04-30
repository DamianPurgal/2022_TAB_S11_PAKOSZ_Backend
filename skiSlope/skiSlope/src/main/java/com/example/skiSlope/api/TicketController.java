package com.example.skiSlope.api;

import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.service.implementations.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

//nowy komentarz
@RequestMapping("api/v1/ticket")
@RestController
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {this.ticketService = ticketService;}

    @PostMapping
    public void addTicket(@Valid @NonNull @RequestBody Ticket ticket){
        ticketService.addTicket(ticket);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping(path="{code}")
    public Ticket getTicketsById(@PathVariable("code") UUID code){
        return ticketService.getTicketById(code)
                .orElse(null);
    }

    @DeleteMapping(path="{code}")
    public void deleteTicketByCode(@PathVariable("code") UUID code){
        ticketService.deleteTicket(code);
    }

    @PutMapping(path="{code}")
    public void updateTicketByCode(@PathVariable("code") UUID code, @Valid @NonNull @RequestBody Ticket ticketToUpdate){
        ticketService.updateTicket(code, ticketToUpdate);
    }


}
