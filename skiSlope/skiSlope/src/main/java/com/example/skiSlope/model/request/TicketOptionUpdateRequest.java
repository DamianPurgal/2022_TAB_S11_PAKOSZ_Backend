package com.example.skiSlope.model.request;

import com.example.skiSlope.model.DiscountType;
import com.example.skiSlope.model.EntriesEnum;
import com.example.skiSlope.model.TicketOption;
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

import static com.example.skiSlope.model.EntriesEnum.transformIntToValue;

@AllArgsConstructor
@Getter
public class TicketOptionUpdateRequest {

    @NumberFormat
    protected Double price;

    @DateTimeFormat
    protected Date startDate;

    @Enumerated(EnumType.STRING)
    protected DiscountType discountType;

    @NumberFormat
    protected Double fullPrice;

    @Enumerated(EnumType.STRING)
    protected EntriesEnum entriesEnum;

    public TicketOption ticketOptionUpdatePriceRequest(TicketOption ticketOption) throws ParseException {
        Date expireDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/9999");
        Long id;
        if(price==null)
            price = ticketOption.getPrice();
        if(discountType==null)
            discountType = ticketOption.getDiscountType();
        if(fullPrice==null)
            fullPrice = ticketOption.getFullPrice();
        if(entriesEnum==null)
            entriesEnum = transformIntToValue(ticketOption.getEntries());
        if(startDate==null){
            id = ticketOption.getId();
            startDate = ticketOption.getStartDate();
        }
        else {
            id = null;
            if (discountType.getValue() == ticketOption.getDiscountType().getValue() && entriesEnum.getValue() == ticketOption.getEntries())
                ticketOptionUpdateExpireDateRequest(ticketOption, startDate);
        }
        return TicketOption.builder()
                .id(id)
                .price(price)
                .startDate(startDate)
                .expireDate(expireDate)
                .discountType(discountType)
                .fullPrice(fullPrice)
                .entriesEnum(entriesEnum)
                .build();
    }
    public TicketOption ticketOptionUpdateExpireDateRequest(TicketOption ticketOption, Date expireDate) throws ParseException {
       //TO implement exception to handle expireDate <= startDate
        return TicketOption.builder()
                .id(ticketOption.getId())
                .price(ticketOption.getPrice())
                .startDate(ticketOption.getStartDate())
                .expireDate(expireDate)
                .discountType(ticketOption.getDiscountType())
                .fullPrice(ticketOption.getFullPrice())
                .entriesEnum(transformIntToValue(ticketOption.getEntries()))
                .build();
    }
}
