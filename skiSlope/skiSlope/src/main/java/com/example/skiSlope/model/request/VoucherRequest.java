package com.example.skiSlope.model.request;

import com.example.skiSlope.model.CardType;
import com.example.skiSlope.model.DiscountType;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.Voucher;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class VoucherRequest {
    //@NotBlank
    @Enumerated(EnumType.STRING)
    //@Size(message = "Discount type must be one of the following : Student, Child, Senior, Disabled, None")
    private DiscountType discountType;

    //@NotBlank
    @NumberFormat
    //@Size(message = "User must exist! Must be a number")
    private Long userId;


    @Size(message = "First name cannot be empty")
    private String ownerName;

    //@NotBlank
    @NumberFormat
    //@Size(message = "Payment id must be in number format")
    private Long paymentId;

    //@NotBlank
    @NumberFormat
    //@Size(message = "Price id must be in number format")
    private Long priceId;

    //@NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    //@Size(message = "Lift id must be in number format")
    private Date startDate;

    //@NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    //@Size(message = "Lift id must be in number format")
    private Date expireDate;

    public Voucher voucherRequestToUser(){
        return Voucher.builder()
                .id(null)
                .discountType(discountType)
                .userId(userId)
                .cardType(CardType.Voucher)
                .ownerName(ownerName)
                .paymentId(paymentId)
                .priceId(priceId)
                .active(true)
                .startDate(startDate)
                .expireDate(expireDate)
                .build();

    }
}
