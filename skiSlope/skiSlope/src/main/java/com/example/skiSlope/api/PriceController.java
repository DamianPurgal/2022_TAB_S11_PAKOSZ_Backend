package com.example.skiSlope.api;

import com.example.skiSlope.model.Price;
import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.repository.PriceRepository;
import com.example.skiSlope.service.implementations.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/prices")
@RestController
public class PriceController {
    private PriceService priceService;

    @GetMapping
    //@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<Price> getAllPrices() {

        return priceService.getAllPrices();
    }
    @GetMapping(path = "{id}")
    //@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Price getVoucherOptionById(@PathVariable("id") Long id) {
        return priceService.getPriceById(id)
                .orElse(null);
    }
//    @GetMapping(path ="startDate({date})")
//    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
//    public List<Price> getPricesByStartDate(@PathVariable("date") Date startDate){
//        return priceRepository.getAllVoucherOptionsByStartDate(startDate);
//    }
//
//    @GetMapping(path ="expireDate({date})")
//    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
//    public List<Price> getPricesByExpireDate(@PathVariable("date") Date expireDate){
//        return priceRepository.getAllVoucherOptionsByExpireDate(expireDate);
//    }
}
