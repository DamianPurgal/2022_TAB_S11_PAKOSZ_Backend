package com.example.skiSlope.model.request;

import com.example.skiSlope.model.FullPrice;
import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.TimePeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@Getter
public class VoucherOptionFullPriceRequest {

    private Date setEternalExpireDate() throws ParseException {
        String expireDateString = "9999-12-31T22:59:59.000-0000";
        return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse(expireDateString);
    }

    public VoucherOption createFullPriceVoucherRequest(DiscountType discountType, TimePeriod timePeriod, FullPrice fullPrice) throws ParseException {
        double countedPrice = fullPrice.getFullPrice()*(1-discountType.getValue())* timePeriod.getValue();
        double fullCountedPrice = fullPrice.getFullPrice()* timePeriod.getValue();
        return VoucherOption.builder()
                .id(null)
                .price(countedPrice)
                .startDate(fullPrice.getStartDate())
                .expireDate(setEternalExpireDate())
                .discountType(discountType)
                .fullPrice(fullCountedPrice)
                .timePeriod(timePeriod)
                .build();
    }
}
