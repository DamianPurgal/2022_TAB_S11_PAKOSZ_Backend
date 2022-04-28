package com.example.skiSlope.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="voucherOptions")
@Table(name="voucheroptions")
public class VoucherOption extends Price{

    @Column(name="time_peroid")
    @Enumerated(EnumType.STRING)
    TimePeriod timePeriod;

}
