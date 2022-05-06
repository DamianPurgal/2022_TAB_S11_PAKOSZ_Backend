package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.PriceNotFoundException;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import com.example.skiSlope.model.request.TicketOptionRequest;
import com.example.skiSlope.model.request.TicketOptionUpdateRequest;
import com.example.skiSlope.repository.TicketOptionRepository;
import com.example.skiSlope.service.definitions.TicketOptionServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.skiSlope.model.enums.EntriesEnum.transformIntToValue;

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
        return ticketOptionRepository.findAllByExpireDateLessThanEqualAndStartDateGreaterThanEqual(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
    }

    @Override
    public List<TicketOption> getAllLatestTicketOptions() throws ParseException {
        return ticketOptionRepository.findAllByExpireDateEquals(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
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
    public void updateLatestTicketOptionData(Date newExpireDate) throws ParseException {
        List<TicketOption> ticketOptionList = ticketOptionRepository.findAllByExpireDateEquals(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
        for(TicketOption t : ticketOptionList){
            t.setExpireDate(newExpireDate);
            ticketOptionRepository.save(t);
        }
    }

    public void updateBeforeLatestTicketOptionData(Date date) throws ParseException {
        List<TicketOption> ticketOptionList = ticketOptionRepository.findAllByExpireDateEquals(date);
        for(TicketOption t : ticketOptionList){
            t.setExpireDate(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
            ticketOptionRepository.save(t);
        }
    }

    @Override
    public void deleteTicketOption(Long id) {
        ticketOptionRepository.deleteById(id);
    }

    @Override
    public void deleteTicketOptionByLatestExpireDate() throws ParseException {
        List<TicketOption> ticketOptions = ticketOptionRepository.findAllByExpireDateEquals(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
        Date foundStartDate = ticketOptions.get(0).getStartDate();
        for(TicketOption t : ticketOptions){
            ticketOptionRepository.delete(t);
        }
        updateBeforeLatestTicketOptionData(foundStartDate);

    }
}