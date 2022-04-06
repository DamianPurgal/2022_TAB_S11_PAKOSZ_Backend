package com.example.skiSlope.api;

import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/voucher")
@RestController
public class VoucherController {

    private final VoucherService voucherService;

    @Autowired
    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @PostMapping
    public void addVoucher(@Valid @NonNull @RequestBody Voucher voucher){
        voucherService.addVoucher(voucher);
    }

    @GetMapping
    public List<Voucher> getAllTickets() {
        return voucherService.getAllVouchers();
    }

    @GetMapping(path="{code}")
    public Voucher getTicketsById(@PathVariable("code") UUID code){
        return voucherService.getVoucherById(code)
                .orElse(null);
    }

    @DeleteMapping(path="{code}")
    public void deleteTicketByCode(@PathVariable("code") UUID code){
        voucherService.deleteVoucher(code);
    }

    @PutMapping(path="{code}")
    public void updateTicketByCode(@PathVariable("code") UUID code, @Valid @NonNull @RequestBody Voucher voucherToUpdate){
        voucherService.updateVoucher(code, voucherToUpdate);
    }
}
