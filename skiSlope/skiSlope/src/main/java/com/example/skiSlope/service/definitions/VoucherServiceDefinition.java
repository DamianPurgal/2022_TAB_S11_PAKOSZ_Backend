package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherServiceDefinition {

    Voucher addVoucher(Voucher voucher);

    Optional<Voucher> getVoucherById(Long id);

    List<Voucher> getAllVouchersByUserId(Long userId);

    List<Voucher> getAllVouchers();

    void updateVouchersData(Voucher Ticket, Long id);

    void deleteVoucher(Long id);
}
