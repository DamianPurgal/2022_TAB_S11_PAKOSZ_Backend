package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Price;
import com.example.skiSlope.model.VoucherOption;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VoucherOptionServiceDefinition {

    VoucherOption addNewVoucherOption(VoucherOption voucherOption);

    Optional<VoucherOption> getVoucherOptionById(Long id);

    List<VoucherOption> getAllVoucherOptions();

    List<VoucherOption> getAllVoucherOptionsByStartDate(Date startDate);

    List<VoucherOption> getAllVoucherOptionsByExpireDate(Date expireDate);

    void updateVoucherOptionData(VoucherOption voucherOption, Long id);

    void deleteVoucherOption(Long id);

}
