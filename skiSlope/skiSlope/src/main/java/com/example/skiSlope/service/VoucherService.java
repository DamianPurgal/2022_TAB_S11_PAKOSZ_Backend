package com.example.skiSlope.service;

import com.example.skiSlope.dao.VoucherDao;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VoucherService {
    private final VoucherDao voucherDao;

    @Autowired
    public VoucherService(VoucherDao voucherDao) {
        this.voucherDao = voucherDao;
    }

    public int addVoucher(Voucher voucher){return voucherDao.insertVoucher(voucher);}

    public List<Voucher> getAllVouchers(){return voucherDao.selectAllVouchers();}

    public Optional<Voucher> getVoucherById(UUID code){return  voucherDao.selectVoucherByCode(code);}

    public int deleteVoucher(UUID code){return voucherDao.deleteVoucherByCode(code);}

    public int updateVoucher(UUID code, Voucher newVoucher){return voucherDao.updateVoucherByCode(code, newVoucher);}
}
