package com.example.skiSlope.service;

import com.example.skiSlope.datasource.exception.VoucherNotFoundException;
import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.repository.VoucherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VoucherService implements VoucherServiceDefinition {

    private VoucherRepository voucherRepository;

    @Override
    public Voucher addVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public Optional<Voucher> getVoucherById(Long id) {
        return Optional.of(voucherRepository.getById(id));
    }

    @Override
    public List<Voucher> getAllVouchersByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public void updateVouchersData(Voucher newVoucher, Long id) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow(VoucherNotFoundException::new);
        voucher = newVoucher;
        voucher.setId(id);
        voucherRepository.save(voucher);
    }

    @Override
    public void deleteVoucher(Long id) {
        voucherRepository.deleteById(id);
    }
}
