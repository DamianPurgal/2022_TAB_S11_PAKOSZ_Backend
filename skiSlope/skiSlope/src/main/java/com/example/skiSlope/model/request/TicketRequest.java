package com.example.skiSlope.model.request;

import com.example.skiSlope.model.CardType;
import com.example.skiSlope.model.DiscountType;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
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

    @NonNull
    @NumberFormat
    private Long paymentId;

    @NonNull
    @NumberFormat
    private Long priceId;

    @NonNull
    @NumberFormat
    private Long liftId;

    @NonNull
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

}
