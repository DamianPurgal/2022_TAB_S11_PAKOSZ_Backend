package com.example.skiSlope.api;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.User;
import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.model.request.TicketRequest;
import com.example.skiSlope.model.request.VoucherRequest;
import com.example.skiSlope.service.implementations.CardService;
import com.example.skiSlope.service.implementations.TicketService;
import com.example.skiSlope.service.implementations.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/card")
@RestController
public class CardController {

    private CardService cardService;


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Card getCardById(@PathVariable("id") Long id) {
        return cardService.getCardById(id)
                .orElse(null);
    }

    @GetMapping("/myCards")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public List<Card> getAllCardsByUserId() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cardService.getAllCardsByUserId(loggedUser.getId());
    }


}
