package com.example.skiSlope.model.request;

import com.example.skiSlope.model.enums.CardType;
//import com.example.skiSlope.model.DiscountType;
import com.example.skiSlope.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class TicketUpdateRequest {

    @Size(message = "First name cannot be empty")
    private String ownerName;

    @NumberFormat
    private Long paymentId;

    @NumberFormat
    private Long priceId;

    @NumberFormat
    private Long liftId;

    @NumberFormat
    private int numberOfEntries;

    private void setIfNull(Ticket updateTicket){
        if(ownerName==null)
            ownerName = updateTicket.getOwnerName();
    }

    public Ticket updateTicket(Ticket updateTicket){
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
//    public Ticket setTicketToInactive(Ticket updateTicket){
//        return Ticket.builder()
//                .id(updateTicket.getId())
//                .code(updateTicket.getCode())
//                .skiLift(updateTicket.getSkiLift())
//                .user(updateTicket.getUser())
//                .price(updateTicket.getPrice())
//                .cardType(CardType.Ticket)
//                .ownerName(updateTicket.getOwnerName())
//                .paymentId(updateTicket.getPaymentId())
//                .active(active)
//                .numberOfEntries(updateTicket.getNumberOfEntries())
//                .build();
//    }
}
