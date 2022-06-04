package com.example.skiSlope.model.request;

import com.example.skiSlope.model.enums.CardType;
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

    private void setIfNull(Voucher voucher){
        if(ownerName==null)
            ownerName = voucher.getOwnerName();
        if(startDate==null)
            startDate = voucher.getStartDate();
        if(expireDate==null)
            expireDate = voucher.getExpireDate();
    }

    public Voucher updateVoucher(Voucher voucher){
        setIfNull(voucher);
        return Voucher.builder()
                .id(voucher.getId())
                .price(voucher.getPrice())
                .startDate(voucher.getStartDate())
                .expireDate(voucher.getExpireDate())
                .user(voucher.getUser())
                .cardType(CardType.Voucher)
                .ownerName(ownerName)
                .payment(voucher.getPayment())
                .price(voucher.getPrice())
                .active(true)
                .startDate(null)
                .expireDate(null)
                .build();
    }
}
