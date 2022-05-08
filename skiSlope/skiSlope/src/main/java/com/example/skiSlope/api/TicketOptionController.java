package com.example.skiSlope.api;

import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import com.example.skiSlope.model.request.TicketOptionUpdateRequest;
import com.example.skiSlope.model.response.TicketOptionResponse;
import com.example.skiSlope.service.implementations.TicketOptionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("api/prices/tickets")
@RestController
public class TicketOptionController {

    private TicketOptionService ticketOptionService;

    private List<TicketOptionResponse> getTicketOptionResponses(List<TicketOption> ticketOptionList) {
        return ticketOptionList.stream().map(
                ticketOptionRes->TicketOptionResponse
                        .builder()
                        .id(ticketOptionRes.getId())
                        .price(BigDecimal.valueOf(ticketOptionRes.getPrice()).setScale(2, RoundingMode.HALF_UP))
                        .startDate(ticketOptionRes.getStartDate())
                        .expireDate(ticketOptionRes.getExpireDate())
                        .entries(ticketOptionRes.getEntries())
                        .discountType(ticketOptionRes.getDiscountType())
                        .fullPrice(BigDecimal.valueOf(ticketOptionRes.getFullPrice()).setScale(2, RoundingMode.HALF_UP))
                        .build()
        ).collect(Collectors.toList());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<TicketOptionResponse> getAllTicketOptions() {
        List<TicketOption> ticketOptionList = ticketOptionService.getAllTicketOptions();
        return getTicketOptionResponses(ticketOptionList);
    }

    @GetMapping("/current")
    @PreAuthorize("permitAll()")
    public List<TicketOptionResponse> getAllCurrentTicketOptions() {
        List<TicketOption> ticketOptionList = ticketOptionService.getAllCurrentTicketOptions();
        return getTicketOptionResponses(ticketOptionList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public TicketOptionResponse getVoucherOptionById(@PathVariable("id") Long id) {
        TicketOption ticketOption = ticketOptionService.getTicketOptionById(id);
        return TicketOptionResponse
                .builder()
                .id(ticketOption.getId())
                .price(BigDecimal.valueOf(ticketOption.getPrice()).setScale(2, RoundingMode.HALF_UP))
                .startDate(ticketOption.getStartDate())
                .expireDate(ticketOption.getExpireDate())
                .entries(ticketOption.getEntries())
                .discountType(ticketOption.getDiscountType())
                .fullPrice(BigDecimal.valueOf(ticketOption.getFullPrice()).setScale(2, RoundingMode.HALF_UP))
                .build();
    }

    @GetMapping("/current/{id}")
    @PreAuthorize("permitAll()")
    public TicketOptionResponse getCurrentTicketOptionById(@PathVariable("id") Long id) {
        TicketOption ticketOption = ticketOptionService.getCurrentTicketOptionById(id);
        return TicketOptionResponse
                .builder()
                .id(ticketOption.getId())
                .price(BigDecimal.valueOf(ticketOption.getPrice()).setScale(2, RoundingMode.HALF_UP))
                .startDate(ticketOption.getStartDate())
                .expireDate(ticketOption.getExpireDate())
                .entries(ticketOption.getEntries())
                .discountType(ticketOption.getDiscountType())
                .fullPrice(BigDecimal.valueOf(ticketOption.getFullPrice()).setScale(2, RoundingMode.HALF_UP))
                .build();
    }

    @DeleteMapping("/latest")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void deleteLatestTicketOptionById() throws ParseException {
        ticketOptionService.deleteTicketOptionByLatestExpireDate();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void updateTicketOptionById(@PathVariable("id") Long id, @Valid @NonNull @RequestBody TicketOptionUpdateRequest ticketOptionUpdateRequest) throws ExpireDateEarlierThanStartDateException, ParseException {
        ticketOptionService.updateTicketOptionsData(ticketOptionUpdateRequest, id);
    }
}
