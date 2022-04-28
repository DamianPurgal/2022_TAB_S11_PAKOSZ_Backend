package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.request.TicketUpdateRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TicketOptionServiceDefinition {

    TicketOption addTicketOption(TicketOption ticketOption);

    List<TicketOption> addTicketOptions(List<TicketOption> ticketOptionList);

    Optional<TicketOption> getTTicketOptionById(Long id);

    List<TicketOption> getAllCurrentTicketOptions();

    List<TicketOption> getAllFutureTicketOptions();

    List<TicketOption> getAllPastTicketOptions();

    List<TicketOption> getAllTicketOptions();

    void updateTicketOptionsData(TicketUpdateRequest ticketUpdateRequest, Long id);

    void deleteTicket(Long id);
}
