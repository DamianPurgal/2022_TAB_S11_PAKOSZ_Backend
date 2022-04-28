package com.example.skiSlope.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Polymorphism(type = PolymorphismType.EXPLICIT)
@Entity
@Table(name="cards")
public abstract class Card {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id", nullable = false)
    protected Price price;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    //@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="code", nullable = false)
    protected UUID code;

//    @Column(name="discount_type")
//    @Enumerated(EnumType.STRING)
//    protected DiscountType discountType;

//    @Column(name = "user_id", nullable = false)
//    protected Long userId;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    protected CardType cardType;

    @Column(name="owner_name")
    protected String ownerName;

    @Column(name="payment_id")
    protected Long paymentId;

//    @Column(name="price_id")
//    protected Long priceId;

    @Column(name="active")
    protected Boolean active;


}
