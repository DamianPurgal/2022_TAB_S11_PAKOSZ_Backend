package com.example.skiSlope.model.request;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Payment;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.Voucher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class PaymentCreateRequest {

    private List<TicketCreatePaymentRequest> tickets;
    private List<VoucherCreatePaymentRequest> vouchers;

    private List<Card> getCardSet(){
        List<Card> cards = new ArrayList<>();
        Ticket ticket = null;
        Voucher voucher = null;
        if(tickets !=null){
            for(TicketCreatePaymentRequest t: tickets){
                ticket = t.ticketRequest();
                cards.add(ticket);
            }
        }
        if(vouchers !=null){
            for(VoucherCreatePaymentRequest v: vouchers){
                voucher = v.voucherRequest();
                cards.add(voucher);
            }
        }
        return cards;
    }
    private Double getTotalCost(){
        List<Card> cards = getCardSet();
        double fullPrice = 0;
        for(Card c: cards){
            fullPrice = fullPrice+c.getPrice().getPrice();
        }
        return fullPrice;
    }

    public Payment paymentRequest(){
        return Payment.builder()
                .id(null)
                .cardSet(null)
                .paymentDate(null)                  //TODO
                .paidOff(false)
                .totalCost(0.0)                    //CHANGE
                .user(null)
                .build();
    }


}
