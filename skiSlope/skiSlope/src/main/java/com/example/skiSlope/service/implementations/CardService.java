package com.example.skiSlope.service.implementations;

import com.example.skiSlope.exception.CardNotFoundException;
import com.example.skiSlope.model.Card;
import com.example.skiSlope.repository.CardRepository;
import com.example.skiSlope.service.definitions.CardServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CardService implements CardServiceDefinition {

    private CardRepository cardRepository;

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);
    }

    @Override
    public List<Card> getAllCardsByUserId(Long userId) {
        List<Card> paidOffCards = new ArrayList<>();
        for(Card c: cardRepository.findCardsByUserId(userId)){
            if(c.getPayment().getPaidOff()) {
                System.out.println(c.getId());
                paidOffCards.add(c);
            }
        }
        return paidOffCards;
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

}
