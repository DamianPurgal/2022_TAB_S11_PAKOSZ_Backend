package com.example.skiSlope.model.request;

import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.model.enums.CardType;
import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.TimePeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.format.annotation.NumberFormat;

@AllArgsConstructor
@Getter
public class VoucherCreatePaymentRequest {

    private String ownerName;

    @NonNull
    @NumberFormat
    private DiscountType discountType;

    @NonNull
    @NumberFormat
    private TimePeriod timePeriod;

    public Voucher voucherRequest(){
        return Voucher.builder()
                .id(null)
                .code(null)
                .cardType(CardType.Voucher)
                .ownerName(ownerName)
                .payment(null)
                .user(null)
                .price(null)
                .active(true)
                .startDate(null)
                .expireDate(null)
                .build();
    }
}
