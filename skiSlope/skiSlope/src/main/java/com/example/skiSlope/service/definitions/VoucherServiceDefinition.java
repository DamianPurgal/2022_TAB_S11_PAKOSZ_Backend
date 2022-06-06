package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Payment;
import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.model.request.VoucherUpdateRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VoucherServiceDefinition {

    Voucher addVoucher(Voucher voucher);

    Voucher getVoucherById(Long id);

    List<Voucher> getAllVouchersByUserId(Long userId);

    List<Voucher> getAllVouchers();

    void updateVouchersData(VoucherUpdateRequest voucherUpdateRequest, Long id);

    void deleteVoucher(Long id);

    Optional<Voucher> getVoucherByCode(UUID code);

    Voucher updateVoucher(Voucher voucher);

    List<Voucher> updateVouchers(List<Voucher> vouchers);

    List<Voucher> setVouchersInactiveIfExpired(List<Voucher> vouchers);

    Voucher setVoucherInactiveIfExpired(Voucher voucher);

    void deleteAllVouchersByPaymentId(Long paymentId);

}
