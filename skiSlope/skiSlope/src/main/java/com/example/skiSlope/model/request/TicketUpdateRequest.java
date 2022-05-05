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
//    @Enumerated(EnumType.STRING)
//    protected DiscountType discountType;

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

    public Ticket updateTicket(Ticket updateTicket){
        return Ticket.builder()
                .id(updateTicket.getId())
//                .discountType(updateTicket.getDiscountType())
//                .userId(updateTicket.getUserId())
                .cardType(CardType.Ticket)
                .ownerName(ownerName)
                .paymentId(updateTicket.getPaymentId())
//                .priceId(updateTicket.getPriceId())
                .active(true)
//                .liftId(updateTicket.getLiftId())
                .numberOfEntries(updateTicket.getNumberOfEntries())
                .build();

    }
    public Ticket setTicketToInactive(Ticket updateTicket){
        return Ticket.builder()
                .id(null)
//                .discountType(updateTicket.getDiscountType())
//                .userId(1234L)
                .cardType(CardType.Ticket)
                .ownerName(updateTicket.getOwnerName())
                .paymentId(updateTicket.getPaymentId())
//                .priceId(updateTicket.getPriceId())
                .active(false)
//                .liftId(updateTicket.getLiftId())
                .numberOfEntries(updateTicket.getNumberOfEntries())
                .build();
    }
}
