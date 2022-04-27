package com.example.skiSlope.api;

import com.example.skiSlope.model.User;
import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.model.request.VoucherRequest;
import com.example.skiSlope.model.request.VoucherUpdateRequest;
import com.example.skiSlope.service.implementations.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/card")
@RestController
public class VoucherController {

    private VoucherService voucherService;

    @PostMapping("/voucher")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public void addVoucher(@Valid @NonNull @RequestBody VoucherRequest voucherRequest) {
        Voucher voucher = voucherRequest.voucherRequestToUser();
        voucherService.addVoucher(voucher);
    }

    @GetMapping("/voucher")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<Voucher> getAllVouchers() {
        return voucherService.getAllVouchers();
    }

    @GetMapping("/voucher/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Voucher getVoucherById(@PathVariable("id") Long id) {
        return voucherService.getVoucherById(id)
                .orElse(null);
    }
    @GetMapping("/myVouchers")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public List<Voucher> getAllVouchersByUserId() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return voucherService.getAllVouchersByUserId(loggedUser.getId());
    }

    @DeleteMapping("/voucher/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void deleteVoucherByCode(@PathVariable("id") Long id) {
        voucherService.deleteVoucher(id);
    }

    @PutMapping("/voucher/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public void updateVoucherByCode(@PathVariable("id") Long id, @Valid @NonNull @RequestBody VoucherUpdateRequest voucherUpdateRequest) {
        voucherService.updateVouchersData(voucherUpdateRequest, id);
    }

}
