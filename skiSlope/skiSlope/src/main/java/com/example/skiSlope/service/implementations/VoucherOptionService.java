package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.PriceNotFoundException;
import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.repository.VoucherOptionRepository;
import com.example.skiSlope.service.definitions.VoucherOptionServiceDefinition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VoucherOptionService implements VoucherOptionServiceDefinition {

    VoucherOptionRepository voucherOptionRepository;

    @Override
    public VoucherOption addNewVoucherOption(VoucherOption voucherOption) {
        return voucherOptionRepository.save(voucherOption);
    }

    @Override
    public Optional<VoucherOption> getVoucherOptionById(Long id) {
        return Optional.of(voucherOptionRepository.getById(id));
    }

    @Override
    public List<VoucherOption> getAllVoucherOptions() {
        return voucherOptionRepository.findAll();
    }

    @Override
    public List<VoucherOption> getAllVoucherOptionsByStartDate(Date startDate) {
        return voucherOptionRepository.findVoucherOptionsByStartDate(startDate);
    }

    @Override
    public List<VoucherOption> getAllVoucherOptionsByExpireDate(Date expireDate) {
        return voucherOptionRepository.findVoucherOptionsByExpireDate(expireDate);
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
    public void deleteVoucherOption(Long id) {voucherOptionRepository.deleteById(id);}


}
