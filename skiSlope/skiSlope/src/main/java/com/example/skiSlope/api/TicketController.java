package com.example.skiSlope.api;

import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/ticket")
@RestController
public class TicketController {

    private TicketService ticketService;

    @PostMapping
    public void addTicket(@Valid @NonNull @RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping(path = "{id}")
    public Ticket getTicketById(@PathVariable("id") Long id) {
        return ticketService.getTicketById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteTicketByCode(@PathVariable("id") Long id) {
        ticketService.deleteTicket(id);
    }

    @PutMapping(path = "{id}")
    public void updateTicketByCode(@PathVariable("id") Long id, @Valid @NonNull @RequestBody Ticket ticketToUpdate) {
        ticketService.updateTicketsData(ticketToUpdate, id);
    }
}
