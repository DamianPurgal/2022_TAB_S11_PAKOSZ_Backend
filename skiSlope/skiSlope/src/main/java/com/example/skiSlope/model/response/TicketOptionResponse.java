package com.example.skiSlope.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketOptionResponse {

    @JsonProperty("price")
    double price;

    @JsonProperty("startDate")
    Date startDate;

    @JsonProperty("expireDate")
    Date expireDate;

    @JsonProperty("entries")
    double entries;

    @JsonProperty("priceWithoutDiscount")
    double fullPrice;
}
