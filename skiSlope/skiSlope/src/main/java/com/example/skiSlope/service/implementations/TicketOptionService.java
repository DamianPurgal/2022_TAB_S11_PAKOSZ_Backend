package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.BusinessException;
import com.example.skiSlope.exception.PriceNotFoundException;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import com.example.skiSlope.model.request.TicketOptionUpdateRequest;
import com.example.skiSlope.repository.TicketOptionRepository;
import com.example.skiSlope.service.definitions.TicketOptionServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public TicketOption getTicketOptionById(Long id) {

        return ticketOptionRepository.findById(id)
                .orElseThrow(PriceNotFoundException::new);
    }

    @Override
    public TicketOption getCurrentTicketOptionById(Long id) {
        return ticketOptionRepository.findByExpireDateGreaterThanEqualAndStartDateLessThanEqualAndId(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), id)
                .orElseThrow(PriceNotFoundException::new);
    }

    @Override
    public List<TicketOption> getAllCurrentTicketOptions() {
        return ticketOptionRepository.findAllByExpireDateGreaterThanEqualAndStartDateLessThanEqual(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
    }

//    @Override
//    public List<TicketOption> getAllLatestTicketOptions() throws ParseException {
//        return ticketOptionRepository.findAllByExpireDateEquals(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
//    }


    @Override
    public List<TicketOption> getAllTicketOptions() {
        return ticketOptionRepository.findAll();
    }

    @Override
    public void updateTicketOptionsData(TicketOptionUpdateRequest ticketOptionUpdateRequest, Long id) throws ParseException {
        TicketOption ticketOption = ticketOptionRepository.findById(id)
                .orElseThrow(PriceNotFoundException::new);
        Date now = new Date(System.currentTimeMillis());
        if(ticketOption.getStartDate().compareTo(now) <= 0){
            throw new BusinessException(HttpStatus.FORBIDDEN.value(), "You cannot update this item becouse it's not a date in the future!");
        }
        ticketOption = ticketOptionUpdateRequest.updatePriceRequest(ticketOption);
        ticketOptionRepository.save(ticketOption);
    }

    @Override
    public void updateLatestTicketOptionData(Date newExpireDate) throws ParseException {
        List<TicketOption> ticketOptionList = ticketOptionRepository.findAllByExpireDateEquals(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
        List<TicketOption> ticketOptionToUpdate = new ArrayList<>();
        for(TicketOption t : ticketOptionList){
            t.setExpireDate(newExpireDate);
            ticketOptionToUpdate.add(t);
        }
        ticketOptionRepository.saveAll(ticketOptionToUpdate);
    }

    @Override
    public void updateBeforeLatestTicketOptionData(Date date) throws ParseException {
        List<TicketOption> ticketOptionList = ticketOptionRepository.findAllByExpireDateEquals(date);
        List<TicketOption> ticketOptionToUpdate = new ArrayList<>();
        for(TicketOption t : ticketOptionList){
            t.setExpireDate(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
            ticketOptionToUpdate.add(t);
        }
        ticketOptionRepository.saveAll(ticketOptionToUpdate);
    }

//    @Override
//    public void deleteTicketOption(Long id) {
//        ticketOptionRepository.deleteById(id);
//    }

    @Override
    public void deleteTicketOptionByLatestExpireDate() throws ParseException {
        List<TicketOption> ticketOptions = ticketOptionRepository.findAllByExpireDateEquals(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
        List<TicketOption> ticketOptionToDelete = new ArrayList<>();
        Date foundStartDate = ticketOptions.get(0).getStartDate();
        Date now = new Date(System.currentTimeMillis());
        if(foundStartDate.compareTo(now) <= 0){
            throw new BusinessException(HttpStatus.FORBIDDEN.value(), "You cannot delete this item becouse it's not a date in the future!");
        }
        for(TicketOption t : ticketOptions){
            ticketOptionToDelete.add(t);
        }
        ticketOptionRepository.deleteAll(ticketOptionToDelete);
        updateBeforeLatestTicketOptionData(foundStartDate);

    }
}