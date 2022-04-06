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

    @Id
    @SequenceGenerator(name = "ticket_sequence", sequenceName = "ticket_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lift_id")
    private  Long liftId;

    @Column(name = "number_of_entries", nullable = false)
    private int numberOfEntries;


    public Ticket(@JsonProperty("id") Long id,
                  @JsonProperty("code") UUID code,
                  @JsonProperty("discount type") DiscountType discountType,
                  @JsonProperty("cost") double cost,
                  @JsonProperty("lift id") Long liftId,
                  @JsonProperty("number of entries") int numberOfEntries,
                  @JsonProperty("customer's id") Long customerId) {
        this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.cost = cost;
        this.liftId = liftId;
        this.numberOfEntries = numberOfEntries;
        this.customerId = customerId;
        this.cardType = CardType.Ticket;
    }
}
