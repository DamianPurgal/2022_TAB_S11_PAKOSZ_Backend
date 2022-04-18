package com.example.skiSlope.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

enum TimePeriod{
    oneHour,
    fourHours,
    day,
    threeDays,
    week,
    twoWeeks,
    month,
    threeMonths,
    year
}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="voucher_options")
public class VoucherOption extends Price{

    @Column(name="time_period")
    @Enumerated(EnumType.STRING)
    TimePeriod timePeriod;

}
