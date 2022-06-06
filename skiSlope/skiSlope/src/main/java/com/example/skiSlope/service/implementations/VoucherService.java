package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.PriceNotFoundException;
import com.example.skiSlope.exception.VoucherNotFoundException;
import com.example.skiSlope.model.Payment;
import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.model.request.VoucherUpdateRequest;
import com.example.skiSlope.repository.VoucherRepository;
import com.example.skiSlope.service.definitions.VoucherServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class VoucherService implements VoucherServiceDefinition {

    private VoucherRepository voucherRepository;

    @Override
    public Voucher addVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher getVoucherById(Long id) { return voucherRepository.findById(id)
            .orElseThrow(VoucherNotFoundException::new); }

    @Override
    public List<Voucher> getAllVouchersByUserId(Long userId) {
        return voucherRepository.findAllByUserId(userId);
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public void updateVouchersData(VoucherUpdateRequest voucherUpdateRequest, Long id) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(VoucherNotFoundException::new);
        voucher = voucherUpdateRequest.updateVoucher(voucher);
        voucherRepository.save(voucher);
    }

    @Override
    public void deleteVoucher(Long id) {
        voucherRepository.findById(id).orElseThrow(VoucherNotFoundException::new);
        voucherRepository.deleteById(id);
    }

    @Override
    public Optional<Voucher> getVoucherByCode(UUID code) {
        return voucherRepository.findByCode(code);
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public List<Voucher> updateVouchers(List<Voucher> vouchers) {
        return voucherRepository.saveAll(vouchers);
    }

    @Override
    public List<Voucher> setVouchersInactiveIfExpired(List<Voucher> vouchers) {
        List<Voucher> vouchersToUpdate = vouchers.stream().filter(Voucher::isVoucherExpired).toList();
        vouchersToUpdate.forEach(voucher -> voucher.setActive(false));
        updateVouchers(vouchersToUpdate);
        return vouchers;
    }

    @Override
    public Voucher setVoucherInactiveIfExpired(Voucher voucher) {
        if(voucher.isVoucherExpired()){
            voucher.setActive(false);
            updateVoucher(voucher);
        }
        return voucher;
    }

    @Override
    public void deleteAllVouchersByPaymentId(Long paymentId) {
        List<Voucher> vouchers = voucherRepository.findAllByPaymentId(paymentId);
        voucherRepository.deleteAll(vouchers);
    }

}
