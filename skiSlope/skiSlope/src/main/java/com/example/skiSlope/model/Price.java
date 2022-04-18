package com.example.skiSlope.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="prices")
public abstract class Price {

    @Id
    @SequenceGenerator(name = "price_sequence", sequenceName = "price_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_sequence")
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name="price", nullable = false)
    protected double price;

    @Column(name="begin_date")
    protected Date startDate;

    @Column(name="expire_date")
    protected Date expireDate;

    @Column(name="discount_type")
    @Enumerated(EnumType.STRING)
    protected DiscountType discountType;

    @Column(name="full_price")
    protected double fullPrice;





}
