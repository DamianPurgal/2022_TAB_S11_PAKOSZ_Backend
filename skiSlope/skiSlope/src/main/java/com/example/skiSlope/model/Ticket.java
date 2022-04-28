package com.example.skiSlope.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
@PrimaryKeyJoinColumn(name="id")
public class Ticket extends Card {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ski_lift_id", nullable = false)
    protected SkiLift skiLift;

//    @Column(name = "ski_lift_id")
//    private  Long liftId;

    @Column(name = "entry_amount")
    private int numberOfEntries;

    @Builder
    public Ticket(@JsonProperty("ticketId") Long id,
                  @JsonProperty("code") UUID code,
//                  @JsonProperty("userId") Long userId,
                  @JsonProperty("paymentId") Long paymentId,
                  @JsonProperty("price") Price price,
//                  @JsonProperty("discountType") DiscountType discountType,
                  @JsonProperty("ownerName") String ownerName,
                  CardType cardType,
                  Boolean active,
                  @JsonProperty("liftId") SkiLift skiLift,
                  @JsonProperty("numberOfEntries") int numberOfEntries) {
        this.id = id;
        this.code = UUID.randomUUID();
//        this.userId = userId;
        this.paymentId = paymentId;
        this.price = price;
//        this.discountType = discountType;
//        if(discountType == null)
//            this.discountType = DiscountType.None;
        this.cardType = CardType.Ticket;
        this.active = true;
        this.ownerName = ownerName;
        System.out.println(ownerName);
        if (ownerName == null){
            User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            this.ownerName = loggedUser.getFirstName()+" "+loggedUser.getLastName();
        }

        this.skiLift = skiLift;
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

