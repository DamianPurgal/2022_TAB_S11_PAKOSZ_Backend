package com.example.skiSlope.service.implementations;

import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.request.TicketUpdateRequest;
import com.example.skiSlope.repository.TicketOptionRepository;
import com.example.skiSlope.service.definitions.TicketOptionServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public Optional<TicketOption> getTTicketOptionById(Long id) {
        return Optional.empty();
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
        return null;
    }

    @Override
    public void updateTicketOptionsData(TicketUpdateRequest ticketUpdateRequest, Long id) {

    }

    @Override
    public void deleteTicket(Long id) {

    }
}
