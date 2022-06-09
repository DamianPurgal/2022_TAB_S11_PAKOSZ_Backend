package com.example.skiSlope.model.request;

import com.example.skiSlope.model.enums.CardType;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.User;
import com.example.skiSlope.model.enums.EntriesEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.context.SecurityContextHolder;


import static com.example.skiSlope.model.enums.EntriesEnum.transformIntToValue;

@AllArgsConstructor
@Getter
public class TicketRequest {

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

    private void setIfNull(){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (ownerName == null){
            this.ownerName = loggedUser.getFirstName()+" "+loggedUser.getLastName();}
    }

    private EntriesEnum intToEnum() {
        EntriesEnum entriesEnum = transformIntToValue(numberOfEntries);
        return entriesEnum;
    }

    public Ticket ticketRequestToUser(){
        setIfNull();
        return Ticket.builder()
                .id(null)
                .code(null)
                .cardType(CardType.Ticket)
                .ownerName(ownerName)
                .payment(null)
                .user(null)
                .price(null)
                .active(null)
                .numberOfEntries(intToEnum().getValue())
                .skiLift(null)
                .build();
    }

}
