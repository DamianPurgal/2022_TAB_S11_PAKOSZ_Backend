package com.example.skiSlope.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "vouchers")
public class Voucher extends Card {

    @Column(name = "begin_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date expireDate;

    public Voucher(@JsonProperty("id") Long id,
                   @JsonProperty("code") UUID code,
                   @JsonProperty("customerId") Long customerId,
                   @JsonProperty("paymentId") Long paymentId,
                   @JsonProperty("priceId") Long priceId,
                   @JsonProperty("discountType") DiscountType discountType,
                   @JsonProperty("ownerName") String ownerName,
                   @JsonProperty("startDate") Date startDate,
                   @JsonProperty("endDate") Date expireDate) {
        this.id = id;
        this.code = code;
        this.userId = customerId;
        this.paymentId = paymentId;
        this.priceId = priceId;
        this.discountType = discountType;
        this.cardType = CardType.Ticket;
        this.active = true;
        this.ownerName = ownerName;
        this.startDate = startDate;
        this.expireDate = expireDate;
    }
}
