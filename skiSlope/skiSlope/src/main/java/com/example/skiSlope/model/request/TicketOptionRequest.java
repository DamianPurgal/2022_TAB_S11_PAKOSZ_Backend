package com.example.skiSlope.model.request;

import com.example.skiSlope.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@Getter
public class TicketOptionRequest {

    @NonNull
    @NumberFormat
    protected double price;

    @NonNull
    @DateTimeFormat
    protected Date startDate;

    @NonNull
    @Enumerated(EnumType.STRING)
    protected DiscountType discountType;

    @NumberFormat
    protected double fullPrice;

    @NonNull
    @Enumerated(EnumType.STRING)
    private EntriesEnum entriesEnum;

    public TicketOption ticketOptionRequest() throws ParseException {
        String expireDateString = "31/12/9999";
        Date expireDate = new SimpleDateFormat("dd/MM/yyyy").parse(expireDateString);
        return TicketOption.builder()
                .id(null)
                .price(price)
                .startDate(startDate)
                .expireDate(expireDate)
                .discountType(discountType)
                .fullPrice(fullPrice)
                .entriesEnum(entriesEnum)
                .build();
    }
}
