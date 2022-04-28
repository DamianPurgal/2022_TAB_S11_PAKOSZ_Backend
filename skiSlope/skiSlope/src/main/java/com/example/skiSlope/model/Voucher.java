package com.example.skiSlope.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vouchers")
@PrimaryKeyJoinColumn(name="id")
public class Voucher extends Card {

    @Column(name = "begin_time")
    private Date startDate;

    @Column(name = "end_time")
    private Date expireDate;

    @Builder
    public Voucher(Long id,
                  @JsonProperty("code") UUID code,
//                  @JsonProperty("userId") Long userId,
                  @JsonProperty("paymentId") Long paymentId,
//                  @JsonProperty("priceId") Long priceId,
//                  @JsonProperty("discountType") DiscountType discountType,
                  @JsonProperty("ownerName") String ownerName,
                  CardType cardType,
                  Boolean active,
                   @JsonProperty("startDate") Date startDate,
                   @JsonProperty("endDate") Date expireDate) {
        this.id = id;
        this.code = UUID.randomUUID();
//        this.userId = userId;
        this.paymentId = paymentId;
//        this.priceId = priceId;
//        this.discountType = discountType;
//        if(discountType == null)
//            this.discountType = DiscountType.None;
        this.cardType = CardType.Voucher;
        this.active = true;
        this.ownerName = ownerName;
        if (ownerName == null){
            User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            this.ownerName = loggedUser.getFirstName()+" "+loggedUser.getLastName();
        }
        this.startDate = startDate;
        this.expireDate = expireDate;
    }
}
