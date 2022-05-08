package com.example.skiSlope.api;

import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.model.response.VoucherOptionResponse;
import com.example.skiSlope.service.implementations.VoucherOptionService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RequestMapping("api/prices/vouchers")
@RestController
public class VoucherOptionController {

    private VoucherOptionService voucherOptionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<VoucherOption> getAllVoucherOptions() {
//        List<VoucherOption> voucherOptionList = voucherOptionService.getAllVoucherOptions();
        return voucherOptionService.getAllVoucherOptions();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public VoucherOption getVoucherOptionById(@PathVariable("id") Long id) {
        return voucherOptionService.getVoucherOptionById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    //@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void deleteVoucherOptionById(@PathVariable("id") Long id) {

        voucherOptionService.deleteVoucherOption(id);
    }

    @PutMapping(path = "{id}")
    //@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void updateVoucherOptionById(@PathVariable("id") Long id, @Valid @NonNull @RequestBody VoucherOption voucherOptionToUpdate) {
        voucherOptionService.updateVoucherOptionData(voucherOptionToUpdate, id);
    }
}
