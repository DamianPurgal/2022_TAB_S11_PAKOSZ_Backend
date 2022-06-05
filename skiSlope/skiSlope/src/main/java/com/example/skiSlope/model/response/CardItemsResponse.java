package com.example.skiSlope.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardItemsResponse {

    List<TicketPaymentResponse> tickets;

    List<VoucherPaymentResponse> vouchers;
}
