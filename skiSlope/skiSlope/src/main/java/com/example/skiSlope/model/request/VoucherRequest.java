package com.example.skiSlope.model.request;

import com.example.skiSlope.model.*;
import jdk.jfr.BooleanFlag;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
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
                .discountType(discountType)
                .userId(loggedUser.getId())
                .cardType(CardType.Voucher)
                .ownerName(ownerName)
                .paymentId(paymentId)
                .priceId(priceId)
                .active(true)
                .startDate(null)
                .expireDate(null)
                .build();
    }
}
