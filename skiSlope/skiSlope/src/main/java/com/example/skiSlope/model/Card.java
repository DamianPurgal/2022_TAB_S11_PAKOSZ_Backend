package com.example.skiSlope.model;

import com.example.skiSlope.model.enums.CardType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Polymorphism(type = PolymorphismType.EXPLICIT)
@Entity
@Table(name="cards")
public abstract class Card {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id", nullable = false)
    protected Price price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    protected Payment payment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name="code", nullable = false)
    @Type(type="org.hibernate.type.UUIDCharType")
    protected UUID code;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    protected CardType cardType;

    @Column(name="owner_name")
    protected String ownerName;

//    @Column(name="payment_id")
//    protected Long paymentId;

    @Column(name="active")
    protected Boolean active;


}
