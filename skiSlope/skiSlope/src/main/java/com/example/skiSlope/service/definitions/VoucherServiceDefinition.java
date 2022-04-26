package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.model.request.VoucherRequest;

import java.util.List;
import java.util.Optional;

public interface VoucherServiceDefinition {

    Voucher addVoucher(Voucher voucher);

    Optional<Voucher> getVoucherById(Long id);

    List<Voucher> getAllVouchersByUserId(Long userId);

    List<Voucher> getAllVouchers();

    void updateVouchersData(VoucherRequest voucherRequest, Long id);

    void deleteVoucher(Long id);
}
