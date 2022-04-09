package com.example.skiSlope.api;

import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.service.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/voucher")
@RestController
public class VoucherController {

    private VoucherService voucherService;

    @PostMapping
    public void addVoucher(@Valid @NonNull @RequestBody Voucher voucher){
        voucherService.addVoucher(voucher);
    }

    @GetMapping
    public List<Voucher> getAllVouchers() {
        return voucherService.getAllVouchers();
    }

    @GetMapping(path="{id}")
    public Voucher getVoucherById(@PathVariable("id") Long id){
        return voucherService.getVoucherById(id)
                .orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deleteVoucherByCode(@PathVariable("id") Long id){
        voucherService.deleteVoucher(id);
    }

    @PutMapping(path="{id}")
    public void updateVoucherByCode(@PathVariable("id") Long id, @Valid @NonNull @RequestBody Voucher voucherToUpdate){
        voucherService.updateVouchersData(voucherToUpdate, id);
    }
}
