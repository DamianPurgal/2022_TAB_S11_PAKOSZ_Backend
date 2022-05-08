package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.BusinessException;
import com.example.skiSlope.exception.PriceNotFoundException;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.model.request.VoucherOptionUpdateRequest;
import com.example.skiSlope.repository.VoucherOptionRepository;
import com.example.skiSlope.service.definitions.VoucherOptionServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.BatchUpdateException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class VoucherOptionService implements VoucherOptionServiceDefinition {

    VoucherOptionRepository voucherOptionRepository;

    @Override
    public VoucherOption addNewVoucherOption(VoucherOption voucherOption) {
        return voucherOptionRepository.save(voucherOption);
    }

    @Override
    public List<VoucherOption> addVoucherOptions(List<VoucherOption> voucherOptionList) {
        return voucherOptionRepository.saveAll(voucherOptionList);
    }

    @Override
    public VoucherOption getVoucherOptionById(Long id) {
        return voucherOptionRepository.findById(id).orElseThrow(PriceNotFoundException::new);
    }

    @Override
    public VoucherOption getCurrentVoucherOptionById(Long id) {
        return voucherOptionRepository.findByExpireDateGreaterThanEqualAndStartDateLessThanEqualAndId(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), id)
                .orElseThrow(PriceNotFoundException::new);
    }

    @Override
    public List<VoucherOption> getAllCurrentVoucherOptions() {
        return voucherOptionRepository.findAllByExpireDateGreaterThanEqualAndStartDateLessThanEqual(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
    }

    @Override
    public List<VoucherOption> getAllVoucherOptions() {
        return voucherOptionRepository.findAll();
    }

    @Override
    public void updateVoucherOptionData(VoucherOptionUpdateRequest voucherOptionUpdateRequest, Long id) throws ParseException {
        VoucherOption voucherOption = voucherOptionRepository.findById(id)
                .orElseThrow(PriceNotFoundException::new);
        Date now = new Date(System.currentTimeMillis());
        if(voucherOption.getStartDate().compareTo(now) <= 0){
            throw new BusinessException(HttpStatus.FORBIDDEN.value(), "You cannot update this item becouse it's not a date in the future!");
        }
        voucherOption = voucherOptionUpdateRequest.updatePriceRequest(voucherOption);
        voucherOptionRepository.save(voucherOption);
    }

    @Override
    public void updateLatestVoucherOptionData(Date newExpireDate) throws ParseException {
        List<VoucherOption> voucherOptionList = voucherOptionRepository.findAllByExpireDateEquals(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
        List<VoucherOption> voucherOptionToUpdate = new ArrayList<>();
        for(VoucherOption v : voucherOptionList){
            v.setExpireDate(newExpireDate);
            voucherOptionToUpdate.add(v);
        }
        voucherOptionRepository.saveAll(voucherOptionToUpdate);
    }

    @Override
    public void updateBeforeLatestVoucherOptionData(Date date) throws ParseException {
        List<VoucherOption> voucherOptionList = voucherOptionRepository.findAllByExpireDateEquals(date);
        List<VoucherOption> voucherOptionToUpdate = new ArrayList<>();
        for(VoucherOption v : voucherOptionList){
            v.setExpireDate(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
            voucherOptionToUpdate.add(v);
        }
        voucherOptionRepository.saveAll(voucherOptionToUpdate);
    }

    @Override
    public void deleteVoucherOptionByLatestExpireDate() throws ParseException {
        List<VoucherOption> voucherOptions = voucherOptionRepository.findAllByExpireDateEquals(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse("9999-12-31T22:59:59.000-0000"));
        List<VoucherOption> voucherOptionToDelete = new ArrayList<>();
        Date foundStartDate = voucherOptions.get(0).getStartDate();
        Date now = new Date(System.currentTimeMillis());
        if(foundStartDate.compareTo(now) <= 0){
            throw new BusinessException(HttpStatus.FORBIDDEN.value(), "You cannot delete this item becouse it's not a date in the future!");
        }
        for(VoucherOption v : voucherOptions){
            voucherOptionToDelete.add(v);
        }
        voucherOptionRepository.deleteAll(voucherOptionToDelete);
        updateBeforeLatestVoucherOptionData(foundStartDate);
    }


}
