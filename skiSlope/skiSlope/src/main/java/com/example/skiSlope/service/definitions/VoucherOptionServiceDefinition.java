package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.TimePeriod;
import com.example.skiSlope.model.request.VoucherOptionUpdateRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface VoucherOptionServiceDefinition {

    VoucherOption addNewVoucherOption(VoucherOption voucherOption);

    List<VoucherOption> addVoucherOptions(List<VoucherOption> voucherOptionList);

    VoucherOption getVoucherOptionById(Long id);

    VoucherOption getCurrentVoucherOptionById(Long id);

    List<VoucherOption> getAllCurrentVoucherOptions();

    List<VoucherOption> getAllVoucherOptions();

    VoucherOption getCurrentVoucherOptionByDiscountTypeAndTimePeriod(DiscountType discountType, TimePeriod timePeriod);

    void updateVoucherOptionData(VoucherOptionUpdateRequest voucherOptionUpdateRequest, Long id) throws ParseException;

    void updateLatestVoucherOptionData(Date newExpireDate) throws ParseException;

    void updateBeforeLatestVoucherOptionData(Date date) throws ParseException;

    void deleteVoucherOptionByLatestExpireDate() throws ParseException;

}
