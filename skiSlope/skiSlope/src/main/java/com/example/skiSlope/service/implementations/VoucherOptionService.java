package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.PriceNotFoundException;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.repository.VoucherOptionRepository;
import com.example.skiSlope.service.definitions.VoucherOptionServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public void updateVoucherOptionData(VoucherOption newVoucherOption, Long id) {
       VoucherOption voucherOption = voucherOptionRepository.findById(id).orElseThrow(PriceNotFoundException::new);

       voucherOption.setPrice(Objects.nonNull(newVoucherOption.getPrice()) ? newVoucherOption.getPrice() : voucherOption.getPrice());
       voucherOption.setStartDate(Objects.nonNull(newVoucherOption.getStartDate()) ? newVoucherOption.getStartDate() : voucherOption.getStartDate());
       voucherOption.setExpireDate(Objects.nonNull(newVoucherOption.getExpireDate()) ? newVoucherOption.getExpireDate() : voucherOption.getExpireDate());
       voucherOption.setDiscountType(Objects.nonNull(newVoucherOption.getDiscountType()) ? newVoucherOption.getDiscountType() : voucherOption.getDiscountType());
       voucherOption.setFullPrice(Objects.nonNull(newVoucherOption.getFullPrice()) ? newVoucherOption.getFullPrice() : voucherOption.getFullPrice());
       voucherOption.setTimePeriod(Objects.nonNull(newVoucherOption.getTimePeriod()) ? newVoucherOption.getTimePeriod() : voucherOption.getTimePeriod());

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
        for(VoucherOption v : voucherOptions){
            voucherOptionToDelete.add(v);
        }
        voucherOptionRepository.deleteAll(voucherOptionToDelete);
        updateBeforeLatestVoucherOptionData(foundStartDate);
    }


}
