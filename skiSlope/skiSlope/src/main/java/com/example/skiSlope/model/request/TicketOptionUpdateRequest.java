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

    @DateTimeFormat
    protected Date startDate;

    @DateTimeFormat
    protected Date expireDate;

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
        if(startDate==null){
            startDate = ticketOption.getStartDate();}
        if(expireDate==null){
            if(ticketOption.getExpireDate()==null){
                expireDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/9999");}
            else {
                expireDate = ticketOption.getExpireDate();}
        }
    }
    private boolean checkExpireDateCorrectness() throws ExpireDateEarlierThanStartDateException {
        if(expireDate.compareTo(startDate) < 0){
            throw new ExpireDateEarlierThanStartDateException();
        }
        return true;
    }
    boolean checkPriceCorrectness(){
        if(price > fullPrice){
            throw new PriceGreaterThanFullPriceException();
        }
        return true;
    }

    public TicketOption updatePriceRequest(TicketOption ticketOption) throws ParseException {
        setNullValues(ticketOption);
        checkExpireDateCorrectness();
        checkPriceCorrectness();
        return TicketOption.builder()
                .id(ticketOption.getId())
                .price(price)
                .startDate(startDate)
                .expireDate(expireDate)
                .discountType(discountType)
                .fullPrice(fullPrice)
                .entriesEnum(entriesEnum)
                .build();
    }
    public TicketOption ticketOptionUpdateExpireDateRequest(TicketOption ticketOption, Date autoExpireDate) throws ParseException{
        setNullValues(ticketOption);
        if(autoExpireDate!=null) {
            expireDate = autoExpireDate;}
        checkExpireDateCorrectness();
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