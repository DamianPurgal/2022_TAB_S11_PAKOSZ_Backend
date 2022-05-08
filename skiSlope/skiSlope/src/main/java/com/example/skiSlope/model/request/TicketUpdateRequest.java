package com.example.skiSlope.model.request;

import com.example.skiSlope.model.enums.CardType;
//import com.example.skiSlope.model.DiscountType;
import com.example.skiSlope.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TicketUpdateRequest {

    private String ownerName;

    private void setIfNull(Ticket updateTicket){
        if(ownerName==null)
            ownerName = updateTicket.getOwnerName();
    }

    public Ticket updateTicketOwnerName(Ticket updateTicket){
        setIfNull(updateTicket);
        return Ticket.builder()
                .id(updateTicket.getId())
                .code(updateTicket.getCode())
                .skiLift(updateTicket.getSkiLift())
                .user(updateTicket.getUser())
                .price(updateTicket.getPrice())
                .cardType(CardType.Ticket)
                .ownerName(ownerName)
                .paymentId(updateTicket.getPaymentId())
                .active(true)
                .numberOfEntries(updateTicket.getNumberOfEntries())
                .build();

    }
}
