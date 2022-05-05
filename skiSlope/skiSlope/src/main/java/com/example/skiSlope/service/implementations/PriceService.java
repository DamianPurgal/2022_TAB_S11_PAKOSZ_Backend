package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.PriceNotFoundException;
import com.example.skiSlope.model.Price;
import com.example.skiSlope.repository.PriceRepository;
import com.example.skiSlope.service.definitions.PriceServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class PriceService implements PriceServiceDefinition {

    PriceRepository priceRepository;

    @Override
    public Price getPriceById(Long id) {
        return Optional.of(priceRepository.getById(id)).orElseThrow(PriceNotFoundException::new);
    }

    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }


    @Override
    public List<Price> getAllPricesByStartDate(Date startDate) {
        return priceRepository.findPricesByStartDate(startDate);
    }

    @Override
    public List<Price> getAllPricesByExpireDate(Date expireDate) {
        return priceRepository.findPricesByExpireDate(expireDate);
    }
}
