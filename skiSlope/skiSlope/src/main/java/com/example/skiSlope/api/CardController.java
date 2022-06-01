package com.example.skiSlope.api;

import com.example.skiSlope.exception.CardNotFoundException;
import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.User;
import com.example.skiSlope.model.response.CardResponse;
import com.example.skiSlope.service.implementations.CardService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("api/card")
@RestController
public class CardController {

    private CardService cardService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public List<CardResponse> getAllCards() {
        List<Card> cardList = cardService.getAllCards();
        return cardList.stream().map(
                cardRes -> CardResponse
                        .builder()
                        .id(cardRes.getId())
                        .code(cardRes.getCode())
                        .type(cardRes.getCardType().toString())
                        .active(cardRes.getActive())
                        .ownerName(cardRes.getOwnerName())
                        .build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public CardResponse getCardById(@PathVariable("id") Long id) {
        Card card = cardService.getCardById(id).orElseThrow(CardNotFoundException::new);
        return CardResponse.builder()
                .id(card.getId())
                .code(card.getCode())
                .ownerName(card.getOwnerName())
                .type(card.getCardType().toString())
                .active(card.getActive())
                .build();
    }

    @GetMapping("/myCards")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    public List<CardResponse> getAllCardsByUserId() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Card> cardList = cardService.getAllCardsByUserId(loggedUser.getId());
        return cardList.stream().map(
                cardRes -> CardResponse
                        .builder()
                        .id(cardRes.getId())
                        .code(cardRes.getCode())
                        .type(cardRes.getCardType().toString())
                        .active(cardRes.getActive())
                        .ownerName(cardRes.getOwnerName())
                        .build()
        ).collect(Collectors.toList());
    }


}
