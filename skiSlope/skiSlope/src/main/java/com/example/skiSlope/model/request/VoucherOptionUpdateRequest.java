package com.example.skiSlope.model.request;

import com.example.skiSlope.exception.PriceGreaterThanFullPriceException;
import com.example.skiSlope.model.TicketOption;
import com.example.skiSlope.model.VoucherOption;
import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.EntriesEnum;
import com.example.skiSlope.exception.ExpireDateEarlierThanStartDateException;
import com.example.skiSlope.model.enums.TimePeriod;
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
public class VoucherOptionUpdateRequest {

    @NumberFormat
    protected Double price;

    @NumberFormat
    protected Double fullPrice;


    private void setNullValues(VoucherOption voucherOption) throws ParseException {
        if(price==null) {
            price = voucherOption.getPrice();}
        if(fullPrice==null) {
            fullPrice = voucherOption.getFullPrice();}
    }
    boolean checkPriceCorrectness(){
        if(price > fullPrice){
            throw new PriceGreaterThanFullPriceException();
        }
        return true;
    }

    public VoucherOption updatePriceRequest(VoucherOption voucherOption) throws ParseException {
        setNullValues(voucherOption);
        checkPriceCorrectness();
        return VoucherOption.builder()
                .id(voucherOption.getId())
                .price(price)
                .startDate(voucherOption.getStartDate())
                .expireDate(voucherOption.getExpireDate())
                .discountType(voucherOption.getDiscountType())
                .fullPrice(fullPrice)
                .timePeriod(voucherOption.getTimePeriod())
                .build();
    }
}