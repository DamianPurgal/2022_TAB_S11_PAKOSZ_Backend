package com.example.skiSlope.api;

import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import com.example.skiSlope.model.FullPrice;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.request.TicketOptionRequest;
import com.example.skiSlope.service.implementations.TicketOptionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@AllArgsConstructor
@RequestMapping("api/v1/prices/set")
@RestController
public class FullPriceController {

    TicketOptionService ticketOptionService;
//
//    @PostMapping("/ticket")
//    public void addNewTicketOption(@Valid @NonNull @RequestBody FullPrice fullPrice)  {
//        TicketOption ticketOption = ticketOptionRequest.ticketOptionRequest();
//        ticketOptionService.addTicketOption(ticketOption);
//    }
}
