package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Price;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.model.request.VoucherOptionUpdateRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VoucherOptionServiceDefinition {

    VoucherOption addNewVoucherOption(VoucherOption voucherOption);

    List<VoucherOption> addVoucherOptions(List<VoucherOption> voucherOptionList);

    VoucherOption getVoucherOptionById(Long id);

    VoucherOption getCurrentVoucherOptionById(Long id);

    List<VoucherOption> getAllCurrentVoucherOptions();

    List<VoucherOption> getAllVoucherOptions();

    void updateVoucherOptionData(VoucherOptionUpdateRequest voucherOptionUpdateRequest, Long id) throws ParseException;

    void updateLatestVoucherOptionData(Date newExpireDate) throws ParseException;

    void updateBeforeLatestVoucherOptionData(Date date) throws ParseException;

    void deleteVoucherOptionByLatestExpireDate() throws ParseException;

}
