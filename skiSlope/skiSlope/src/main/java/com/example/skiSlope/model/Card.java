package com.example.skiSlope.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
//@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="cards")
public abstract class Card {

    @Id
    @SequenceGenerator(name = "card_sequence", sequenceName = "card_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="code", nullable = false)
    protected UUID code;

    @Column(name="discount_type")
    protected DiscountType discountType;

    @Column(name = "customers_id", nullable = false)
    protected Long customerId;

    @Column(name="cost")
    protected double cost;

    @Column(name="type")
    protected CardType cardType;

    /*Card(Long id, UUID code, DiscountType discountType, Long customerId, double cost, CardType cardType){
        this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.customerId = customerId;
        this.cost = cost;
        this.cardType = cardType;
    }*/
//    protected void modify(Card card){
//        this.code = card.code;
//        this.discountType = card.discountType;
//        this.customerId = card.customerId;
//        this.cost = card.cost;
//        this.cardType = card.cardType;
//    }

}
