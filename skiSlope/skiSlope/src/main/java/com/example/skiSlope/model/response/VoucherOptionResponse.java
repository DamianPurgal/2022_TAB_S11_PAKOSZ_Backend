package com.example.skiSlope.model.response;

import com.example.skiSlope.model.enums.DiscountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoucherOptionResponse {

    @JsonProperty("id")
    Long id;

    @JsonProperty("price")
    BigDecimal price;

    @JsonProperty("startDate")
    Date startDate;

    @JsonProperty("expireDate")
    Date expireDate;

    @JsonProperty("discountType")
    @Enumerated(EnumType.STRING)
    DiscountType discountType;

    @JsonProperty("timePeriod")
    String timePeriod;

    @JsonProperty("priceWithoutDiscount")
    BigDecimal fullPrice;
}
