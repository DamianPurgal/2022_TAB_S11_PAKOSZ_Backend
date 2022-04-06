package com.example.skiSlope.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "vouchers")
public class Voucher extends Card{
    @Id
    @SequenceGenerator(name = "voucher_sequence", sequenceName = "voucher_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voucher_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="begin_date")
    private Date startDate;

    @Column(name="end_date")
    private Date expireDate;

    public Voucher(@JsonProperty("id") Long id,
                  @JsonProperty("code") UUID code,
                  @JsonProperty("discount type") DiscountType discountType,
                  @JsonProperty("cost") double cost,
                  @JsonProperty("start date") Date startDate,
                  @JsonProperty("end date") Date expireDate,
                  @JsonProperty("customer's id") Long customerId) {
        this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.cost = cost;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.customerId = customerId;
        this.cardType = CardType.Voucher;
    }
}
