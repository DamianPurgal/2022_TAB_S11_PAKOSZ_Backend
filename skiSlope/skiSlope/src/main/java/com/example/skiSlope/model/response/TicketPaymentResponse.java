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
public class TicketPaymentResponse {

    private String ownerName;

    private String skiLiftName;

    private String discountType;

    private int entryAmount;

    private BigDecimal ticketPrice;
}
