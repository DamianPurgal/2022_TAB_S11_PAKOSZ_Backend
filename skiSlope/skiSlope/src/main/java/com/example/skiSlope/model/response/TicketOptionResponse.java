package com.example.skiSlope.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketOptionResponse {

    @JsonProperty("price")
    BigDecimal price;

    @JsonProperty("startDate")
    Date startDate;

    @JsonProperty("expireDate")
    Date expireDate;

    @JsonProperty("entries")
    int entries;

    @JsonProperty("priceWithoutDiscount")
    BigDecimal fullPrice;
}
