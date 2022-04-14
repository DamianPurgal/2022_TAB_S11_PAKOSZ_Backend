package com.example.skiSlope.service;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CardService implements CardServiceDefinition {

    private CardRepository cardRepository;

    @Override
    public Optional<Card> getCardById(Long id) {
        return Optional.of(cardRepository.getById(id));
    }

    @Override
    public List<Card> getAllCardsByUserId(Long userId) {
        return cardRepository.findCardsByUserId(userId);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }


}
