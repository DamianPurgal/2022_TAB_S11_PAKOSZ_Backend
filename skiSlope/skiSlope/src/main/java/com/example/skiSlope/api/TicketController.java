package com.example.skiSlope.api;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.User;
import com.example.skiSlope.model.request.TicketRequest;
import com.example.skiSlope.service.implementations.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/card")
@RestController
public class TicketController {

    private TicketService ticketService;

    @PostMapping("/ticket")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public void addTicket(@Valid @NonNull @RequestBody TicketRequest ticketRequest) {
        Ticket ticket = ticketRequest.ticketRequestToUser();
        ticketService.addTicket(ticket);
    }

    @GetMapping("/ticket")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/ticket/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public Ticket getTicketById(@PathVariable("id") Long id) {
        return ticketService.getTicketById(id)
                .orElse(null);
    }
    @GetMapping("/myTickets")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public List<Ticket> getAllTicketsByUserId() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ticketService.getAllTicketsByUserId(loggedUser.getId());
    }

    @DeleteMapping("/ticket/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public void deleteTicketByCode(@PathVariable("id") Long id) {
        ticketService.deleteTicket(id);
    }

    @PutMapping("/ticket/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public void updateTicketByCode(@PathVariable("id") Long id, @Valid @NonNull @RequestBody TicketRequest ticketRequest) {
        ticketService.updateTicketsData(ticketRequest, id);
    }

}
