package com.example.skiSlope.api;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.service.CardService;
import com.example.skiSlope.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/card")
@RestController
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping(path="{code}")
    public Card getTicketsById(@PathVariable("code") UUID code){
        return cardService.getCardById(code)
                .orElse(null);
    }
}
