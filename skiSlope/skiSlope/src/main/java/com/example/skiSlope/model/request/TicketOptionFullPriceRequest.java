package com.example.skiSlope.model.request;

import com.example.skiSlope.model.FullPrice;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.EntriesEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@Getter
public class TicketOptionFullPriceRequest {

    private Date setEternalExpireDate() throws ParseException {
        String expireDateString = "9999-12-31T22:59:59.000-0000";
        return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse(expireDateString);
    }

    public TicketOption createFullPricePriceRequest(DiscountType discountType, EntriesEnum entriesEnum, FullPrice fullPrice) throws ParseException {
        return TicketOption.builder()
                .id(null)
                .price(fullPrice.getFullPrice()*discountType.getValue()* entriesEnum.getValue())
                .startDate(fullPrice.getStartDate())
                .expireDate(setEternalExpireDate())
                .discountType(discountType)
                .fullPrice(fullPrice.getFullPrice())
                .entriesEnum(entriesEnum)
                .build();
    }
}
