package com.example.skiSlope.model.request;

import com.example.skiSlope.exception.PriceGreaterThanFullPriceException;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.EntriesEnum;
import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.skiSlope.model.enums.EntriesEnum.transformIntToValue;

@AllArgsConstructor
@Getter
public class TicketOptionUpdateRequest {

    @NumberFormat
    protected Double price;

    @NumberFormat
    protected Double fullPrice;


    private void setNullValues(TicketOption ticketOption) {
        if(price==null) {
            price = ticketOption.getPrice();}
        if(fullPrice==null) {
            fullPrice = ticketOption.getFullPrice();}
    }
    boolean checkPriceCorrectness(){
        if(price > fullPrice){
            throw new PriceGreaterThanFullPriceException();
        }
        return true;
    }

    public TicketOption updatePriceRequest(TicketOption ticketOption) {
        setNullValues(ticketOption);
        checkPriceCorrectness();
        return TicketOption.builder()
                .id(ticketOption.getId())
                .price(price)
                .startDate(ticketOption.getStartDate())
                .expireDate(ticketOption.getExpireDate())
                .discountType(ticketOption.getDiscountType())
                .fullPrice(fullPrice)
                .entriesEnum(EntriesEnum.transformIntToValue(ticketOption.getEntries()))
                .build();
    }
}