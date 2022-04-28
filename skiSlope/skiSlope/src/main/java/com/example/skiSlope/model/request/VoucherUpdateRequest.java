package com.example.skiSlope.model.request;

import com.example.skiSlope.model.CardType;
import com.example.skiSlope.model.Voucher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@Getter
public class VoucherUpdateRequest {

    private String ownerName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    private Date startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    private Date expireDate;


    public Voucher updateVoucher(Voucher voucher){
        if(ownerName==null)
            ownerName = voucher.getOwnerName();
        if(startDate==null)
            startDate = voucher.getStartDate();
        if(expireDate==null)
            expireDate = voucher.getExpireDate();
        System.out.println("Voucher request");
        return Voucher.builder()
                .id(voucher.getId())
//                .discountType(voucher.getDiscountType())
//                .userId(1234L)
                .cardType(CardType.Voucher)
                .ownerName(ownerName)
                .paymentId(voucher.getPaymentId())
//                .priceId(1234L)
                .active(true)
                .startDate(null)
                .expireDate(null)
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
}
