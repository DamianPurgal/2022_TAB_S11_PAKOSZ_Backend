package com.example.skiSlope.api;

import com.example.skiSlope.exception.BusinessException;
import com.example.skiSlope.model.*;
import com.example.skiSlope.model.request.VoucherRequest;
import com.example.skiSlope.model.request.VoucherUpdateRequest;
import com.example.skiSlope.model.response.VoucherResponse;
import com.example.skiSlope.service.definitions.UserService;
import com.example.skiSlope.service.implementations.PriceService;
import com.example.skiSlope.service.implementations.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("Hello 0");
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user2 = userService.getUser(loggedUser.getUsername());
        Price price = priceService.getPriceById(voucherRequest.getPriceId());
        System.out.println("Hello");

        Voucher voucher = voucherRequest.voucherRequestToUser();
        voucher.setPrice(price);
        voucher.setUser(user2);
        voucherService.addVoucher(voucher);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<VoucherResponse> getAllVouchers() {
        List<Voucher> voucherList = voucherService.getAllVouchers();

        voucherList = voucherService.setVouchersInactiveIfExpired(voucherList);

        return voucherList.stream().map(
                voucherRes->VoucherResponse
                        .builder()
                        .id(voucherRes.getId())
                        .code(voucherRes.getCode())
                        .active(voucherRes.getActive())
                        .ownerName(voucherRes.getOwnerName())
                        .startDate(voucherRes.getStartDate())
                        .expireDate(voucherRes.getExpireDate())
                        .build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public VoucherResponse getVoucherById(@PathVariable("id") Long id) {
        Voucher voucher = voucherService.getVoucherById(id);

        voucher = voucherService.setVoucherInactiveIfExpired(voucher);

        return VoucherResponse.builder()
                .id(voucher.getId())
                .code(voucher.getCode())
                .ownerName(voucher.getOwnerName())
                .active(voucher.getActive())
                .ownerName(voucher.getOwnerName())
                .startDate(voucher.getStartDate())
                .expireDate(voucher.getExpireDate())
                .build();
    }



    @GetMapping("/myVouchers")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public List<VoucherResponse> getAllVouchersByUserId() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Voucher> voucherList = voucherService.getAllVouchersByUserId(loggedUser.getId());

        voucherList = voucherService.setVouchersInactiveIfExpired(voucherList);

        return voucherList.stream().map(
                voucherRes->VoucherResponse
                        .builder()
                        .id(voucherRes.getId())
                        .code(voucherRes.getCode())
                        .active(voucherRes.getActive())
                        .ownerName(voucherRes.getOwnerName())
                        .startDate(voucherRes.getStartDate())
                        .expireDate(voucherRes.getExpireDate())
                        .build()
        ).collect(Collectors.toList());
    }
    @GetMapping("/myVouchers/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public VoucherResponse getUserVoucherById(@PathVariable("id") Long id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Voucher voucher = voucherService.getVoucherById(id);

        voucher = voucherService.setVoucherInactiveIfExpired(voucher);

        if (loggedUser.getId().equals(voucher.getUser().getId())){
            return VoucherResponse.builder()
                    .id(voucher.getId())
                    .code(voucher.getCode())
                    .ownerName(voucher.getOwnerName())
                    .active(voucher.getActive())
                    .ownerName(voucher.getOwnerName())
                    .startDate(voucher.getStartDate())
                    .expireDate(voucher.getExpireDate())
                    .build();
            }
        else {
            throw new BusinessException(HttpStatus.FORBIDDEN.value(), "You don't have permission to that voucher resource!");
        }
    }

    @DeleteMapping("/myVouchers/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public void deleteVoucherById(@PathVariable("id") Long id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Voucher voucher = voucherService.getVoucherById(id);
        if (loggedUser.getId().equals(voucher.getUser().getId())) {
            voucherService.deleteVoucher(id);
        }
        else {
            throw new BusinessException(HttpStatus.FORBIDDEN.value(), "You don't have permission to delete that voucher!");
        }
    }

    @PutMapping("/myVouchers/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public void updateVoucherById(@PathVariable("id") Long id, @Valid @NonNull @RequestBody VoucherUpdateRequest voucherUpdateRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Voucher voucher = voucherService.getVoucherById(id);
        if (loggedUser.getId().equals(voucher.getUser().getId())) {
            voucherService.updateVouchersData(voucherUpdateRequest, id);
        }
        else {
            throw new BusinessException(HttpStatus.FORBIDDEN.value(), "You don't have permission to update that voucher!");
        }
    }

}
