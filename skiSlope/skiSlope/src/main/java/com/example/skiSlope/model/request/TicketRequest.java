package com.example.skiSlope.model.request;

import com.example.skiSlope.model.CardType;
import com.example.skiSlope.model.DiscountType;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.User;
import com.example.skiSlope.security.UserRole;
import com.google.errorprone.annotations.FormatString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class TicketRequest {

    //@NotBlank
    @Enumerated(EnumType.STRING)
    //@Size(message = "Discount type must be one of the following : Student, Child, Senior, Disabled, None")
    private DiscountType discountType;

    //@NotBlank
    @NumberFormat
    //@Size(message = "User must exist! Must be a number")
    private Long userId;


    @Size(message = "First name cannot be empty")
    private String ownerName;

    //@NotBlank
    @NumberFormat
    //@Size(message = "Payment id must be in number format")
    private Long paymentId;

    //@NotBlank
    @NumberFormat
    //@Size(message = "Price id must be in number format")
    private Long priceId;

    //@NotBlank
    @NumberFormat
    //@Size(message = "Lift id must be in number format")
    private Long liftId;

    //@NotBlank
    @NumberFormat
    //@Size(message = "Lift id must be in number format")
    private int numberOfEntries;

    public Ticket ticketRequestToUser(){
        return Ticket.builder()
                .id(null)
                .discountType(discountType)
                .userId(userId)
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
