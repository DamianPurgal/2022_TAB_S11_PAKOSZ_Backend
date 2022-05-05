package com.example.skiSlope.api;

import com.example.skiSlope.model.*;
import com.example.skiSlope.model.request.VoucherRequest;
import com.example.skiSlope.model.request.VoucherUpdateRequest;
import com.example.skiSlope.service.definitions.UserService;
import com.example.skiSlope.service.implementations.PriceService;
import com.example.skiSlope.service.implementations.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RequestMapping("api/card/voucher")
@RestController
public class VoucherController {

    private VoucherService voucherService;
    private PriceService priceService;
    private UserService userService;

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public void addVoucher(@Valid @NonNull @RequestBody VoucherRequest voucherRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user2 = userService.getUser(loggedUser.getUsername());

        Voucher voucher = voucherRequest.voucherRequestToUser();
        voucher.setPrice(priceService.getPriceById(voucherRequest.getPriceId()));
        voucher.setUser(user2);
        voucherService.addVoucher(voucher);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<Voucher> getAllVouchers() {
        return voucherService.getAllVouchers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Voucher getVoucherById(@PathVariable("id") Long id) {
        return voucherService.getVoucherById(id);
    }
    @GetMapping("/myVouchers")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public List<Voucher> getAllVouchersByUserId() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return voucherService.getAllVouchersByUserId(loggedUser.getId());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void deleteVoucherByCode(@PathVariable("id") Long id) {
        voucherService.deleteVoucher(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public void updateVoucherByCode(@PathVariable("id") Long id, @Valid @NonNull @RequestBody VoucherUpdateRequest voucherUpdateRequest) {
        voucherService.updateVouchersData(voucherUpdateRequest, id);
    }

}
