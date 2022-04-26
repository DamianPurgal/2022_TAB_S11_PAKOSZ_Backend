package com.example.skiSlope.model.request;

import com.example.skiSlope.model.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

import static com.example.skiSlope.model.TimePeriod.*;

@AllArgsConstructor
@Getter
public class VoucherRequest {
    
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;


    @Size(message = "First name cannot be empty")
    private String ownerName;

    //@NotBlank
    @NumberFormat
    private Long paymentId;

    //@NotBlank
    @NumberFormat
    private Long priceId;

    //@NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    private Date startDate;

    //@NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    private Date expireDate;

    @BooleanFlag
    private Boolean active;

    public Voucher voucherRequestToUser(){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Voucher.builder()
                .id(null)
                .discountType(discountType)
                .userId(loggedUser.getId())
                .cardType(CardType.Voucher)
                .ownerName(ownerName)
                .paymentId(paymentId)
                .priceId(priceId)
                .active(true)
                .startDate(startDate)
                .expireDate(expireDate)
                .build();
    }
    public Voucher updateVoucher(Voucher voucher){
        if(ownerName==null)
            ownerName = voucher.getOwnerName();
        if(active==null)
            active = voucher.getActive();
        if(startDate==null)
            startDate = voucher.getStartDate();
        if(expireDate==null)
            expireDate = voucher.getExpireDate();
        System.out.println("Voucher request");
        return Voucher.builder()
                .id(voucher.getId())
                .discountType(voucher.getDiscountType())
                .userId(voucher.getUserId())
                .cardType(CardType.Voucher)
                .ownerName(ownerName)
                .paymentId(voucher.getPaymentId())
                .priceId(voucher.getPriceId())
                .active(active)
                .startDate(startDate)
                .expireDate(expireDate)
                .build();
    }
    public Voucher setVoucherInactive(Voucher voucher){
        return Voucher.builder()
                .active(false)
                .build();
    }
    public Voucher setDates(Voucher voucher){
        //Implement time
        return Voucher.builder()
                .startDate(startDate)
                .expireDate(expireDate)
                .build();
    }
    void transformTimePeriodToTimeAmount(Date startDate, TimePeriod timePeriod){
//        int hours, days, years;
//        switch (timePeriod ){
//            case oneHour:
//                hours = 1;
//                expireDate.
//                break;
//            case fourHours:
//                hours = 4;
//                break;
//                case
//
//        }


    }
}
