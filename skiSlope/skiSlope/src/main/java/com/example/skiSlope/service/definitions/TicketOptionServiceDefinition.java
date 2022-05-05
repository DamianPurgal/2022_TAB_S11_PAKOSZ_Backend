package com.example.skiSlope.service.definitions;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import com.example.skiSlope.model.request.TicketOptionUpdateRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TicketOptionServiceDefinition {

    TicketOption addTicketOption(TicketOption ticketOption);

    List<TicketOption> addTicketOptions(List<TicketOption> ticketOptionList);

    Optional<TicketOption> getTicketOptionById(Long id);

    List<TicketOption> getAllCurrentTicketOptions();

    List<TicketOption> getAllLatestTicketOptions() throws ParseException;

//    List<TicketOption> getAllPastTicketOptions();

    List<TicketOption> getAllTicketOptions();

    void updateTicketOptionsData(TicketOptionUpdateRequest ticketOptionUpdateRequest, Long id) throws ExpireDateEarlierThanStartDateException, ParseException;

    void updateLatestTicketOptionData(Date newExpireDate) throws ParseException;

    void deleteTicketOption(Long id);

    void deleteTicketOptionByLatestExpireDate(Date expireDate);
}