package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.PriceNotFoundException;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import com.example.skiSlope.model.request.TicketOptionUpdateRequest;
import com.example.skiSlope.repository.TicketOptionRepository;
import com.example.skiSlope.service.definitions.TicketOptionServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class TicketOptionService implements TicketOptionServiceDefinition {

    TicketOptionRepository ticketOptionRepository;

    @Override
    public TicketOption addTicketOption(TicketOption ticketOption) {
        return ticketOptionRepository.save(ticketOption);
    }

    @Override
    public List<TicketOption> addTicketOptions(List<TicketOption> ticketOptionList) {
        return ticketOptionRepository.saveAll(ticketOptionList);
    }

    @Override
    public Optional<TicketOption> getTicketOptionById(Long id) {
        return ticketOptionRepository.findById(id);
    }

    @Override
    public List<TicketOption> getAllCurrentTicketOptions() {
        return null;
    }

    @Override
    public List<TicketOption> getAllFutureTicketOptions() {
        return null;
    }

    @Override
    public List<TicketOption> getAllPastTicketOptions() {
        return null;
    }

    @Override
    public List<TicketOption> getAllTicketOptions() {
        return ticketOptionRepository.findAll();
    }

    @Override
    public void updateTicketOptionsData(TicketOptionUpdateRequest ticketOptionUpdateRequest, Long id) throws ExpireDateEarlierThanStartDateException, ParseException {
        TicketOption ticketOption = ticketOptionRepository.findById(id)
                .orElseThrow(PriceNotFoundException::new);
        ticketOption = ticketOptionUpdateRequest.updatePriceRequest(ticketOption);
        ticketOptionRepository.save(ticketOption);
    }

    @Override
    public void deleteTicketOption(Long id) {
        ticketOptionRepository.deleteById(id);
    }
}