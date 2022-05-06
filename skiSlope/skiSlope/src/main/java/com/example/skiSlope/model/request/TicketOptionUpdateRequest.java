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

    @Enumerated(EnumType.STRING)
    protected DiscountType discountType;

    @NumberFormat
    protected Double fullPrice;

    @Enumerated(EnumType.STRING)
    protected EntriesEnum entriesEnum;

    private void setNullValues(TicketOption ticketOption) throws ParseException {
        if(price==null) {
            price = ticketOption.getPrice();}
        if(discountType==null) {
            discountType = ticketOption.getDiscountType();}
        if(fullPrice==null) {
            fullPrice = ticketOption.getFullPrice();}
        if(entriesEnum==null) {
            entriesEnum = transformIntToValue(ticketOption.getEntries());}
    }
    boolean checkPriceCorrectness(){
        if(price > fullPrice){
            throw new PriceGreaterThanFullPriceException();
        }
        return true;
    }

    public TicketOption updatePriceRequest(TicketOption ticketOption) throws ParseException {
        setNullValues(ticketOption);
        checkPriceCorrectness();
        return TicketOption.builder()
                .id(ticketOption.getId())
                .price(price)
                .startDate(ticketOption.getStartDate())
                .expireDate(ticketOption.getExpireDate())
                .discountType(discountType)
                .fullPrice(fullPrice)
                .entriesEnum(entriesEnum)
                .build();
    }
//    public TicketOption ticketOptionUpdateExpireDateRequest(TicketOption ticketOption, Date autoExpireDate) throws ParseException{
//        setNullValues(ticketOption);
//        if(autoExpireDate!=null) {
//            expireDate = autoExpireDate;}
//        checkExpireDateCorrectness();
//        return TicketOption.builder()
//                .id(ticketOption.getId())
//                .price(ticketOption.getPrice())
//                .startDate(ticketOption.getStartDate())
//                .expireDate(expireDate)
//                .discountType(ticketOption.getDiscountType())
//                .fullPrice(ticketOption.getFullPrice())
//                .entriesEnum(transformIntToValue(ticketOption.getEntries()))
//                .build();
//    }
}