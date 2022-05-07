package com.example.skiSlope.api;

import com.example.skiSlope.model.Price;
import com.example.skiSlope.service.implementations.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/prices")
@RestController
public class PriceController {
    private PriceService priceService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<Price> getAllPrices() {
        return priceService.getAllPrices();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Price getPriceById(@PathVariable("id") Long id) {
        return priceService.getPriceById(id);
    }

    @GetMapping("/current")
    @PreAuthorize("permitAll()")
    public List<Price> getAllCurrentPrices() {
        return priceService.getAllCurrentPrices();
    }

    @GetMapping("/current/{id}")
    @PreAuthorize("permitAll()")
    public Price getCurrentPriceById(@PathVariable("id") Long id) {
        return priceService.getCurrentPriceById(id);
    }
}
