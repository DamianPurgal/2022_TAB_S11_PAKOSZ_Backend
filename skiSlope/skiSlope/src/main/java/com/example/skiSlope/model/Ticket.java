package com.example.skiSlope.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket extends Card {

    @Column(name = "lift_id")
    private  Long liftId;

    @Column(name = "number_of_entries", nullable = false)
    private int numberOfEntries;

    @Builder
    public Ticket(Long id,
                  @JsonProperty("code") UUID code,
                  @JsonProperty("userId") Long userId,
                  @JsonProperty("paymentId") Long paymentId,
                  @JsonProperty("priceId") Long priceId,
                  @JsonProperty("discountType") DiscountType discountType,
                  @JsonProperty("ownerName") String ownerName,
                  CardType cardType,
                  Boolean active,
                  @JsonProperty("liftId") Long liftId,
                  @JsonProperty("numberOfEntries") int numberOfEntries) {
        this.id = id;
        this.code = UUID.randomUUID();
        this.userId = userId;
        this.paymentId = paymentId;
        this.priceId = priceId;
        this.discountType = discountType;
        if(discountType == null)
            this.discountType = DiscountType.None;
        this.cardType = CardType.Ticket;
        this.active = true;
        this.ownerName = ownerName;

        this.liftId = liftId;
        this.numberOfEntries = numberOfEntries;

    }

    /***
     * Decreases the number of entries after each ticket scan
     */
    void decreaseEntries(){
        if(numberOfEntries>0)
            numberOfEntries--;
        if(numberOfEntries<=0)
            setActive(false);
    }
}

