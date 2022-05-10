package com.example.skiSlope.api;

import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.model.request.TicketOptionUpdateRequest;
import com.example.skiSlope.model.request.VoucherOptionUpdateRequest;
import com.example.skiSlope.model.response.TicketOptionResponse;
import com.example.skiSlope.model.response.VoucherOptionResponse;
import com.example.skiSlope.service.implementations.VoucherOptionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("api/prices/vouchers")
@RestController
public class VoucherOptionController {

    private VoucherOptionService voucherOptionService;

    private List<VoucherOptionResponse> getVoucherOptionResponses(List<VoucherOption> voucherOptionList) {
        return voucherOptionList.stream().map(
                voucherOption->VoucherOptionResponse
                        .builder()
                        .id(voucherOption.getId())
                        .price(BigDecimal.valueOf(voucherOption.getPrice()).setScale(2, RoundingMode.HALF_UP))
                        .startDate(voucherOption.getStartDate())
                        .expireDate(voucherOption.getExpireDate())
                        .timePeriod(voucherOption.getTimePeriod().getName())
                        .discountType(voucherOption.getDiscountType())
                        .fullPrice(BigDecimal.valueOf(voucherOption.getFullPrice()).setScale(2, RoundingMode.HALF_UP))
                        .build()
        ).collect(Collectors.toList());
    }
    private VoucherOptionResponse getVoucherOptionResponse(VoucherOption voucherOption){
        return VoucherOptionResponse
                .builder()
                .id(voucherOption.getId())
                .price(BigDecimal.valueOf(voucherOption.getPrice()).setScale(2, RoundingMode.HALF_UP))
                .startDate(voucherOption.getStartDate())
                .expireDate(voucherOption.getExpireDate())
                .timePeriod(voucherOption.getTimePeriod().getName())
                .discountType(voucherOption.getDiscountType())
                .fullPrice(BigDecimal.valueOf(voucherOption.getFullPrice()).setScale(2, RoundingMode.HALF_UP))
                .build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<VoucherOptionResponse> getAllVoucherOptions() {
        List<VoucherOption> voucherOptionList = voucherOptionService.getAllVoucherOptions();
        return getVoucherOptionResponses(voucherOptionList);
    }

    @GetMapping("/current")
    @PreAuthorize("permitAll()")
    public List<VoucherOptionResponse> getAllCurrentVoucherOptions() {
        List<VoucherOption> voucherOptionList = voucherOptionService.getAllCurrentVoucherOptions();
        return getVoucherOptionResponses(voucherOptionList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public VoucherOptionResponse getVoucherOptionById(@PathVariable("id") Long id) {
        VoucherOption voucherOption = voucherOptionService.getVoucherOptionById(id);
       return getVoucherOptionResponse(voucherOption);
    }

    @GetMapping("/current/{id}")
    @PreAuthorize("permitAll()")
    public VoucherOptionResponse getCurrentVoucherOptionById(@PathVariable("id") Long id) {
        VoucherOption voucherOption = voucherOptionService.getCurrentVoucherOptionById(id);
        return getVoucherOptionResponse(voucherOption);
    }

    @DeleteMapping("/latest")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void deleteLatestVoucherOptionById() throws ParseException {
        voucherOptionService.deleteVoucherOptionByLatestExpireDate();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public void updateVoucherOptionById(@PathVariable("id") Long id, @Valid @NonNull @RequestBody VoucherOptionUpdateRequest voucherOptionUpdateRequest) throws ParseException {
        voucherOptionService.updateVoucherOptionData(voucherOptionUpdateRequest, id);
    }
}
