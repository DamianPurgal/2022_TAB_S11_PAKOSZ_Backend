package com.example.skiSlope.service.definitions;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.request.TicketOptionUpdateRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TicketOptionServiceDefinition {

    TicketOption addTicketOption(TicketOption ticketOption);

    List<TicketOption> addTicketOptions(List<TicketOption> ticketOptionList);

    TicketOption getTicketOptionById(Long id);

    TicketOption getCurrentTicketOptionById(Long id);

    List<TicketOption> getAllCurrentTicketOptions();

    List<TicketOption> getAllTicketOptions();

    TicketOption getTicketOptionByCurrentDateAndDiscountTypeAndEntries(DiscountType discountType, int entries);

    void updateTicketOptionsData(TicketOptionUpdateRequest ticketOptionUpdateRequest, Long id) throws ExpireDateEarlierThanStartDateException, ParseException;

    void updateLatestTicketOptionData(Date newExpireDate) throws ParseException;

    void updateBeforeLatestTicketOptionData(Date date) throws ParseException;

    void deleteTicketOptionByLatestExpireDate() throws ParseException;
}