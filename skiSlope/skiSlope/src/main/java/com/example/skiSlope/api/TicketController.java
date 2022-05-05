package com.example.skiSlope.api;

import com.example.skiSlope.exception.PriceNotFoundException;
import com.example.skiSlope.model.SkiLift;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.User;
import com.example.skiSlope.model.request.TicketRequest;
import com.example.skiSlope.model.request.TicketUpdateRequest;
import com.example.skiSlope.model.response.TicketResponse;
import com.example.skiSlope.service.definitions.UserService;
import com.example.skiSlope.service.implementations.PriceService;
import com.example.skiSlope.service.implementations.SkiLiftService;
import com.example.skiSlope.service.implementations.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("api/card/ticket")
@RestController
public class TicketController {

    private TicketService ticketService;
    private UserService userService;
    private PriceService priceService;
    private SkiLiftService skiLiftService;


    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public void addTicket(@Valid @NonNull @RequestBody TicketRequest ticketRequest) throws PriceNotFoundException, PriceNotFoundException {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user2 = userService.getUser(loggedUser.getUsername());

        Ticket ticket = ticketRequest.ticketRequestToUser();
        SkiLift skiLift = skiLiftService.getSkyLiftById(ticketRequest.getLiftId());
        ticket.setPrice(priceService.getPriceById(ticketRequest.getPriceId()));
        ticket.setSkiLift(skiLift);
        ticket.setUser(user2);
        ticketService.addTicket(ticket);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<TicketResponse> getAllTickets() {
        List<Ticket> ticketList = ticketService.getAllTickets();
        return ticketList.stream().map(
                ticketRes->TicketResponse
                        .builder()
                        .id(ticketRes.getId())
                        .code(ticketRes.getCode())
                        .active(ticketRes.getActive())
                        .entryAmount(ticketRes.getNumberOfEntries())
                        .ownerName(ticketRes.getOwnerName())
                        .skiLiftName(ticketRes.getSkiLift().getName())
                        .build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Ticket getTicketById(@PathVariable("id") Long id) {
        return ticketService.getTicketById(id)
                .orElse(null);
    }
    @GetMapping("/myTickets")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public List<TicketResponse> getAllTicketsByUserId() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user2 = userService.getUser(loggedUser.getUsername());
//        return user2.getTicketSet().stream().map(
//                ticketRes->TicketResponse
//                        .builder()
//                        .id(ticketRes.getId())
//                        .code(ticketRes.getCode())
//                        .userId(ticketRes.getUser().getId())
//                        .active(ticketRes.getActive())
//                        .entryAmount(ticketRes.getNumberOfEntries())
//                        .ownerName(ticketRes.getOwnerName())
//                        .build()
//        ).collect(Collectors.toList());
        List<Ticket> ticketList = ticketService.getAllTicketsByUserId(loggedUser.getId());
        return ticketList.stream().map(
                ticketRes->TicketResponse
                        .builder()
                        .id(ticketRes.getId())
                        .code(ticketRes.getCode())
                        .active(ticketRes.getActive())
                        .entryAmount(ticketRes.getNumberOfEntries())
                        .ownerName(ticketRes.getOwnerName())
                        .skiLiftName(ticketRes.getSkiLift().getName())
                        .build()
        ).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void deleteTicketByCode(@PathVariable("id") Long id) {
        ticketService.deleteTicket(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public void updateTicketByCode(@PathVariable("id") Long id, @Valid @NonNull @RequestBody TicketUpdateRequest ticketUpdateRequest) {
        ticketService.updateTicketsData(ticketUpdateRequest, id);
    }

}