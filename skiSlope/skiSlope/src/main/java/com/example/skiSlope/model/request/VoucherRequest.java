package com.example.skiSlope.model.request;

import com.example.skiSlope.model.*;
import com.example.skiSlope.model.enums.CardType;
import com.example.skiSlope.model.enums.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@Getter
public class VoucherRequest {
    
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private String ownerName;

    @NonNull
    @NumberFormat
    private Long paymentId;

    @NonNull
    @NumberFormat
    private Long priceId;


    public Voucher voucherRequestToUser(){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Voucher.builder()
                .id(null)
//                .discountType(discountType)
//                .userId(loggedUser.getId())
                .cardType(CardType.Voucher)
                .ownerName(ownerName)
                .paymentId(paymentId)
                .user(null)
                .price(null)
                .active(true)
                .startDate(null)
                .expireDate(null)
                .build();
    }
}
