package com.example.skiSlope.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="prices")
@Table(name="prices")
public abstract class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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

    @Column(name="discount_parameter")
    protected double fullPrice;





}
