package com.example.skiSlope.service.implementations;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.repository.CardRepository;
import com.example.skiSlope.service.definitions.CardServiceDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class CardService implements CardServiceDefinition {

    private CardRepository cardRepository;

    @Override
    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
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
