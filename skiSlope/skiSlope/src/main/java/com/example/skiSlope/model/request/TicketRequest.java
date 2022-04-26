package com.example.skiSlope.model.request;

import com.example.skiSlope.model.CardType;
import com.example.skiSlope.model.DiscountType;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class TicketRequest {

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;


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

    public Ticket ticketRequestToUser(){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Ticket.builder()
                .id(null)
                .discountType(discountType)
                .userId(loggedUser.getId())
                .cardType(CardType.Ticket)
                .ownerName(ownerName)
                .paymentId(paymentId)
                .priceId(priceId)
                .active(true)
                .liftId(liftId)
                .numberOfEntries(numberOfEntries)
                .build();

    }
    public Ticket updateTicket(Ticket updateTicket){
        System.out.println(ownerName);
        return Ticket.builder()
                .id(updateTicket.getId())
                .discountType(updateTicket.getDiscountType())
                .userId(updateTicket.getUserId())
                .cardType(CardType.Ticket)
                .ownerName(ownerName)
                .paymentId(updateTicket.getPaymentId())
                .priceId(updateTicket.getPriceId())
                .active(true)
                .liftId(updateTicket.getLiftId())
                .numberOfEntries(updateTicket.getNumberOfEntries())
                .build();

    }
    public Ticket setTicketToInactive(Ticket updateTicket){
        return Ticket.builder()
                .id(null)
                .discountType(updateTicket.getDiscountType())
                .userId(updateTicket.getUserId())
                .cardType(CardType.Ticket)
                .ownerName(updateTicket.getOwnerName())
                .paymentId(updateTicket.getPaymentId())
                .priceId(updateTicket.getPriceId())
                .active(false)
                .liftId(updateTicket.getLiftId())
                .numberOfEntries(updateTicket.getNumberOfEntries())
                .build();
    }
}
