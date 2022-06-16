package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.PriceNotFoundException;
import com.example.skiSlope.exception.SkiLiftNotFoundException;
import com.example.skiSlope.model.Price;
import com.example.skiSlope.repository.PriceRepository;
import com.example.skiSlope.service.definitions.PriceServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PriceService implements PriceServiceDefinition {

    PriceRepository priceRepository;

    @Override
    public Price getPriceById(Long id) {
        return priceRepository.findById(id)
                .orElseThrow(PriceNotFoundException::new);
    }

    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Price getCurrentPriceById(Long id) {
        return priceRepository.findByExpireDateGreaterThanEqualAndStartDateLessThanEqualAndId(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), id)
                .orElseThrow(PriceNotFoundException::new);
    }

    @Override
    public List<Price> getAllCurrentPrices() {
        return priceRepository.findAllByExpireDateGreaterThanEqualAndStartDateLessThanEqual(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
    }
}
