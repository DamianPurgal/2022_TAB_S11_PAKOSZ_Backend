package com.example.skiSlope.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

enum DiscountType{
    None,
    Child,
    Student,
    Senior,
    Disabled
}

enum CardType{
    Ticket,
    Voucher
}

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="cards")
public abstract class Card {

    @Id
    @SequenceGenerator(name = "card_sequence", sequenceName = "card_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_sequence")
    @Column(name = "id", nullable = false)
    protected Long id;

    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="code", nullable = false)
    protected UUID code;

    @Column(name="discountType")
    protected DiscountType discountType;

    @Column(name = "customerId", nullable = false)
    protected Long userId;

    @Column(name="type")
    protected CardType cardType;

    @Column(name="ownerName")
    protected String ownerName;

    @Column(name="paymentId")
    protected Long paymentId;

    @Column(name="priceId")
    protected Long priceId;

    @Column(name="active")
    protected Boolean active;

    //implement method to search for a user by theirs id
    void setOwnerNameWhenNull(){
        if(ownerName==null){

        }
    }

}
