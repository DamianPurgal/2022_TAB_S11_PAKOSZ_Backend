package com.example.skiSlope.model;

import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.EntriesEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ticketoptions")
public class TicketOption extends Price {

    @Column(name="entries")
    protected int entries;

    @Builder
    public TicketOption(Long id, double price, Date startDate, Date expireDate, DiscountType discountType, double fullPrice, EntriesEnum entriesEnum){
        this.id = id;
        this.price = price;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.discountType = discountType;
        this.fullPrice = fullPrice;
        this.entries = entriesEnum.getValue();
    }

}