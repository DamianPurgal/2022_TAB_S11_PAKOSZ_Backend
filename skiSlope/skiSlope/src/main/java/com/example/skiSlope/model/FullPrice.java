package com.example.skiSlope.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class FullPrice {

    @JsonProperty("priceWithoutDiscount")
    Double fullPrice;

    @JsonProperty("startDate")
    Date startDate;

}
