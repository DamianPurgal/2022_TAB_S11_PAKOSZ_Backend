package com.example.skiSlope.dao;

import com.example.skiSlope.model.Voucher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VoucherDao {

    int insertVoucher(UUID code, Voucher voucher);

    default int insertVoucher(Voucher voucher){
        UUID code = UUID.randomUUID();
        return insertVoucher(code, voucher);
    }

    List<Voucher> selectAllVouchers();

    Optional<Voucher> selectVoucherByCode(UUID code);

    int deleteVoucherByCode(UUID code);
    int updateVoucherByCode(UUID code, Voucher ticket);
}
