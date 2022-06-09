package com.example.skiSlope.model;

import com.example.skiSlope.model.enums.CardType;
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

    @Column(name = "entry_amount")
    private int numberOfEntries;

    @Builder
    public Ticket(Long id, UUID code, User user, Payment payment, Price price, String ownerName, CardType cardType, Boolean active, SkiLift skiLift, int numberOfEntries) {
        this.id = id;
        this.code = UUID.randomUUID();
        this.payment = payment;
        this.user = user;
        this.price = price;
        this.cardType = CardType.Ticket;
        this.active = false;
        this.ownerName = ownerName;
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

