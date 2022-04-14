package com.example.skiSlope.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket extends Card {

    @Column(name = "lift_id")
    private  Long liftId;

    @Column(name = "number_of_entries", nullable = false)
    private int numberOfEntries;


    public Ticket(@JsonProperty("id") Long id,
                  @JsonProperty("code") UUID code,
                  @JsonProperty("customerId") Long customerId,
                  @JsonProperty("paymentId") Long paymentId,
                  @JsonProperty("priceId") Long priceId,
                  @JsonProperty("discountType") DiscountType discountType,
                  @JsonProperty("ownerName") String ownerName,
                  @JsonProperty("liftId") Long liftId,
                  @JsonProperty("numberOfEntries") int numberOfEntries)

                   {
        this.id = id;
        this.code = code;
        this.userId = customerId;
        this.paymentId = paymentId;
        this.priceId = priceId;
        this.discountType = discountType;
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
