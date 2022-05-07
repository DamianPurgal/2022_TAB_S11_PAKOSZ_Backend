package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Price;
import com.example.skiSlope.model.TicketOption;

import java.util.Date;
import java.util.List;

public interface PriceServiceDefinition {

    Price getPriceById(Long id);

    List<Price> getAllPrices();

    Price getCurrentPriceById(Long id);

    List<Price> getAllCurrentPrices();

//    List<Price> getAllPricesByStartDate(Date startDate);
//
//    List<Price> getAllPricesByExpireDate(Date expireDate);
}
