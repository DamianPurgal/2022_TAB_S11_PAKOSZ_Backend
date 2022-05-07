package com.example.skiSlope.model;

import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.EntriesEnum;
import com.example.skiSlope.model.enums.TimePeriod;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="voucheroptions")
public class VoucherOption extends Price{

    @Column(name="time_peroid")
    @Enumerated(EnumType.STRING)
    TimePeriod timePeriod;

    @Builder
    public VoucherOption(Long id, double price, Date startDate, Date expireDate, DiscountType discountType, double fullPrice, TimePeriod timePeriod){
        this.id = id;
        this.price = price;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.discountType = discountType;
        this.fullPrice = fullPrice;
        this.timePeriod = timePeriod;
    }

}
