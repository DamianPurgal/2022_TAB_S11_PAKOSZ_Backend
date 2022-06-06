package com.example.skiSlope.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoucherPaymentResponse {

    private String ownerName;

    private String periodTime;

    private String discountType;

    private BigDecimal voucherPrice;

}
