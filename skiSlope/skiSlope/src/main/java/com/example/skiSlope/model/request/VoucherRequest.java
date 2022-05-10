package com.example.skiSlope.model.request;

import com.example.skiSlope.model.*;
import com.example.skiSlope.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.context.SecurityContextHolder;

@AllArgsConstructor
@Getter
public class VoucherRequest {

    private String ownerName;

    @NonNull
    @NumberFormat
    private Long paymentId;

    @NonNull
    @NumberFormat
    private Long priceId;

    private void setIfNull(){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (ownerName == null){
            this.ownerName = loggedUser.getFirstName()+" "+loggedUser.getLastName();}
    }

    public Voucher voucherRequestToUser(){
        setIfNull();
        return Voucher.builder()
                .id(null)
                .code(null)
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
