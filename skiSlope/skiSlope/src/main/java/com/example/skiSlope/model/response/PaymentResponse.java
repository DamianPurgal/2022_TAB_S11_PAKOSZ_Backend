package com.example.skiSlope.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    BigDecimal totalPrice;
    boolean paidOff;
    Date payDate;
    CardItemsResponse items;


}
