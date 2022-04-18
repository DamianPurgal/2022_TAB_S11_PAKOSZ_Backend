package com.example.skiSlope.api;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.User;
import com.example.skiSlope.service.implementations.CardService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/card")
@RestController
public class CardController {

    private CardService cardService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_CUSTOMER')")
    public Card getCardById(@PathVariable("id") Long id) {
        return cardService.getCardById(id)
                .orElse(null);
    }

    @GetMapping(path="/myCards")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public List<Card> getAllCardsByUserId() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cardService.getAllCardsByUserId(loggedUser.getId());
    }

}
