package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Price;

import java.util.Date;
import java.util.List;

public interface PriceServiceDefinition {

    Price getPriceById(Long id);

    List<Price> getAllPrices();

    List<Price> getAllPricesByStartDate(Date startDate);

    List<Price> getAllPricesByExpireDate(Date expireDate);
}
