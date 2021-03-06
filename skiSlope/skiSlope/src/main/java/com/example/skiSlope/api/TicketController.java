package com.example.skiSlope.api;

import com.example.skiSlope.exception.BusinessException;
import com.example.skiSlope.model.Price;
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
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public void addTicket(@Valid @NonNull @RequestBody TicketRequest ticketRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user2 = userService.getUser(loggedUser.getUsername());
        Price price = priceService.getPriceById(ticketRequest.getPriceId());
        SkiLift skiLift = skiLiftService.getSkyLiftById(ticketRequest.getLiftId());

        Ticket ticket = ticketRequest.ticketRequestToUser();
        ticket.setPrice(price);
        ticket.setSkiLift(skiLift);
        ticket.setUser(user2);
        ticketService.addTicket(ticket);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<TicketResponse> getAllTickets() {
        List<Ticket> ticketList = ticketService.getAllTickets();
        List<Ticket> paidOffTicketList = getPaidOffTicketList(ticketList);
        return paidOffTicketList.stream().map(
                ticketRes -> TicketResponse
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
    public TicketResponse getTicketById(@PathVariable("id") Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return TicketResponse.builder()
                .id(ticket.getId())
                .code(ticket.getCode())
                .ownerName(ticket.getOwnerName())
                .entryAmount(ticket.getNumberOfEntries())
                .skiLiftName(ticket.getSkiLift().getName())
                .active(ticket.getActive())
                .build();
    }

    @GetMapping("/myTickets")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public List<TicketResponse> getAllTicketsByUserId() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Ticket> ticketList = ticketService.getAllTicketsByUserId(loggedUser.getId());
        List<Ticket> paidOffTicketList = getPaidOffTicketList(ticketList);
        return paidOffTicketList.stream().map(
                ticketRes -> TicketResponse
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

    @GetMapping("/myTickets/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public TicketResponse getUserTicketById(@PathVariable("id") Long id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Ticket ticket = ticketService.getTicketById(id);
        if (loggedUser.getId().equals(ticket.getUser().getId())) {
            if (ticketService.getTicketById(id).getPayment().getPaidOff())
                return TicketResponse.builder()
                        .id(ticket.getId())
                        .code(ticket.getCode())
                        .ownerName(ticket.getOwnerName())
                        .entryAmount(ticket.getNumberOfEntries())
                        .skiLiftName(ticket.getSkiLift().getName())
                        .active(ticket.getActive())
                        .build();
            else
                throw new BusinessException(HttpStatus.FORBIDDEN.value(), "Ticket isn't paid off!");
        } else {
            throw new BusinessException(HttpStatus.FORBIDDEN.value(), "You don't have permission to that ticket resource!");
        }
    }

    @GetMapping("/myTickets/skiLiftId/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public List<TicketResponse> getAllUserTicketsBySkiLiftId(@PathVariable("id") Long id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SkiLift skiLift = skiLiftService.getSkyLiftById(id);
        List<Ticket> ticketList = ticketService.getAllTicketsByUserId(loggedUser.getId());
        List<Ticket> ticketListBySkiLift = new ArrayList<>();
        for (Ticket t : ticketList) {
            if (t.getSkiLift().getId().equals(skiLift.getId())) {
                ticketListBySkiLift.add(t);
            }
        }
        List<Ticket> paidOffTicketList = getPaidOffTicketList(ticketListBySkiLift);
        return paidOffTicketList.stream().map(
                ticketRes -> TicketResponse
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

    @GetMapping("/skiLiftId/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<TicketResponse> getAllTicketsBySkiLiftId(@PathVariable("id") Long id) {
        SkiLift skiLift = skiLiftService.getSkyLiftById(id);
        List<Ticket> ticketList = ticketService.getAllTicketsBySkiLiftId(skiLift.getId());
        return ticketList.stream().map(
                ticketRes -> TicketResponse
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

    @DeleteMapping("/myTickets/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public void deleteTicketByCode(@PathVariable("id") Long id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Ticket ticket = ticketService.getTicketById(id);
        if (loggedUser.getId().equals(ticket.getUser().getId())) {
            ticketService.deleteTicket(id);
        } else {
            throw new BusinessException(HttpStatus.FORBIDDEN.value(), "You don't have permission to delete that ticket!");
        }
    }

    @PutMapping("/myTickets/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public void updateTicketByCode(@PathVariable("id") Long id, @Valid @NonNull @RequestBody TicketUpdateRequest ticketUpdateRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ticketService.updateTicketsData(ticketUpdateRequest, id, loggedUser);
    }

    private List<Ticket> getPaidOffTicketList(List<Ticket> tickets) {
        List<Ticket> paidOfTickets = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getPayment().getPaidOff()) {
                paidOfTickets.add(t);
            }
        }
        return paidOfTickets;
    }

}