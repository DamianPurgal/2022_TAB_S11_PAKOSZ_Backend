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

    private List<TicketRequest> ticketRequests;
    private List<VoucherRequest>voucherRequests;

    private List<Card> getCardSet(){
        List<Card> cards = new ArrayList<>();
        Ticket ticket = null;
        Voucher voucher = null;
        if(ticketRequests!=null){
            for(TicketRequest t: ticketRequests){
                ticket = t.ticketRequestToUser();
                cards.add(ticket);
            }
        }
        if(voucherRequests!=null){
            for(VoucherRequest v: voucherRequests){
                voucher = v.voucherRequestToUser();
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
                .cardSet(getCardSet())
                .paymentDate(null)                  //TODO
                .paidOff(false)
                .totalCost(getTotalCost())
                .user(null)
                .build();
    }


}
