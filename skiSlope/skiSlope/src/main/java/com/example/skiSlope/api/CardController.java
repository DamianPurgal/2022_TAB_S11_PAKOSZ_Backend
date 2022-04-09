package com.example.skiSlope.api;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.service.CardService;
import com.example.skiSlope.service.TicketService;
import com.example.skiSlope.service.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("api/v1/card")
@RestController
public class CardController {

    private CardService cardService;


    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping(path="{id}")
    public Card getCardById(@PathVariable("id") Long id){
        return cardService.getCardById(id)
                .orElse(null);
    }

}
