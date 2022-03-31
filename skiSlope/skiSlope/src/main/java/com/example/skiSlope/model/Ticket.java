package com.example.skiSlope.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class Ticket implements Card{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private  UUID id;

    @Column(name = "discount_type")
    private DiscountType discountType;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "lift_id")
    private  UUID liftId;

    @Column(name = "number_of_entries", nullable = false)
    private int numberOfEntries;

    @Column(name = "customers_id", nullable = false)
    private UUID customerId;


    public Ticket(@JsonProperty("code") UUID code,
                  @JsonProperty("discount type") DiscountType discountType,
                  @JsonProperty("cost") double cost,
                  @JsonProperty("lift id") UUID liftId,
                  @JsonProperty("number of entries") int numberOfEntries,
                  @JsonProperty("customer's id") UUID customerId) {
        this.id = code;
        this.discountType = discountType;
        this.cost = cost;
        this.liftId = liftId;
        this.numberOfEntries = numberOfEntries;
        this.customerId = customerId;
    }

    public Ticket() {

    }

    public UUID getId() {return id;}

    @Override
    public DiscountType getDiscountType() {return discountType;}

    @Override
    public double getCost() {return cost;}

    public UUID getLiftId() {return liftId;}

    public int getNumberOfEntries() {return numberOfEntries;}

    public UUID getCustomerId() {return customerId;}
}
