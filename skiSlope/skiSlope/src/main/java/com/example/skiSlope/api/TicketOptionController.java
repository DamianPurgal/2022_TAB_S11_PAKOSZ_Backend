package com.example.skiSlope.api;

import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import com.example.skiSlope.model.request.TicketOptionRequest;
import com.example.skiSlope.model.request.TicketOptionUpdateRequest;
import com.example.skiSlope.model.response.TicketOptionResponse;
import com.example.skiSlope.service.implementations.TicketOptionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("api/prices/tickets")
@RestController
public class TicketOptionController {

    private TicketOptionService ticketOptionService;

    @PostMapping
    public void addNewTicketOption(@Valid @NonNull @RequestBody TicketOptionRequest ticketOptionRequest) throws ExpireDateEarlierThanStartDateException, ParseException {
        TicketOption ticketOption = ticketOptionRequest.ticketOptionRequest();
        ticketOptionService.addTicketOption(ticketOption);
    }

    @GetMapping
    public List<TicketOptionResponse> getAllTicketOptions() {
        List<TicketOption> ticketOptionList = ticketOptionService.getAllTicketOptions();
        return ticketOptionList.stream().map(
                ticketOptionRes->TicketOptionResponse
                        .builder()
                        .price(BigDecimal.valueOf(ticketOptionRes.getPrice()).setScale(2, RoundingMode.HALF_UP))
                        .startDate(ticketOptionRes.getStartDate())
                        .expireDate(ticketOptionRes.getExpireDate())
                        .entries(ticketOptionRes.getEntries())
                        .fullPrice(BigDecimal.valueOf(ticketOptionRes.getFullPrice()).setScale(2, RoundingMode.HALF_UP))
                        .build()
        ).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public TicketOptionResponse getVoucherOptionById(@PathVariable("id") Long id) {
        TicketOption ticketOption = ticketOptionService.getTicketOptionById(id)
                .orElse(null);

        return TicketOptionResponse
                .builder()
                .price(BigDecimal.valueOf(ticketOption.getPrice()).setScale(2, RoundingMode.HALF_UP))
                .startDate(ticketOption.getStartDate())
                .expireDate(ticketOption.getExpireDate())
                .entries(ticketOption.getEntries())
                .fullPrice(BigDecimal.valueOf(ticketOption.getFullPrice()).setScale(2, RoundingMode.HALF_UP))
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteVoucherOptionById(@PathVariable("id") Long id) {
        ticketOptionService.deleteTicketOption(id);
    }

    @PutMapping("/{id}")
    public void updateVoucherOptionById(@PathVariable("id") Long id, @Valid @NonNull @RequestBody TicketOptionUpdateRequest ticketOptionUpdateRequest) throws ExpireDateEarlierThanStartDateException, ParseException {
        ticketOptionService.updateTicketOptionsData(ticketOptionUpdateRequest, id);
    }
}
