package com.example.skiSlope.model.request;

import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.enums.CardType;
import com.example.skiSlope.model.enums.DiscountType;
import com.example.skiSlope.model.enums.EntriesEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.format.annotation.NumberFormat;

import static com.example.skiSlope.model.enums.EntriesEnum.transformIntToValue;

@AllArgsConstructor
@Getter
public class TicketCreatePaymentRequest {

    private String ownerName;

    @NonNull
    @NumberFormat
    private DiscountType discountType;

    @NonNull
    @NumberFormat
    private int numberOfEntries;

    @NonNull
    @NumberFormat
    private String skiLiftName;

    private EntriesEnum intToEnum() {
        EntriesEnum entriesEnum = transformIntToValue(numberOfEntries);
        return entriesEnum;
    }

    public Ticket ticketRequest(){
        return Ticket.builder()
                .id(null)
                .code(null)
                .cardType(CardType.Ticket)
                .ownerName(ownerName)
                .payment(null)
                .user(null)
                .price(null)
                .active(true)
                .numberOfEntries(intToEnum().getValue())
                .skiLift(null)
                .build();
    }
}
