package com.example.skiSlope.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    Long id;
    String payment;
    Double maxHeight;
    Double skiRunLength;
    String description;
    Boolean active;
}
