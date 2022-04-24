package com.example.skiSlope.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vouchers")
public class Voucher extends Card {

    @Column(name = "begin_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date expireDate;

    @Builder
    public Voucher(Long id,
                  @JsonProperty("code") UUID code,
                  @JsonProperty("userId") Long userId,
                  @JsonProperty("paymentId") Long paymentId,
                  @JsonProperty("priceId") Long priceId,
                  @JsonProperty("discountType") DiscountType discountType,
                  @JsonProperty("ownerName") String ownerName,
                  CardType cardType,
                  Boolean active,
                   @JsonProperty("startDate") Date startDate,
                   @JsonProperty("endDate") Date expireDate) {
        this.id = id;
        this.code = UUID.randomUUID();
        this.userId = userId;
        this.paymentId = paymentId;
        this.priceId = priceId;
        this.discountType = discountType;
        if(discountType == null)
            this.discountType = DiscountType.None;
        this.cardType = CardType.Voucher;
        this.active = true;
        this.ownerName = ownerName;

        this.startDate = startDate;
        this.expireDate = expireDate;
    }
}
