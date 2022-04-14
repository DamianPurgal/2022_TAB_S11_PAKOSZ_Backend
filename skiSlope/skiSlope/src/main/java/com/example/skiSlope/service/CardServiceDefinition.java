package com.example.skiSlope.service;

import com.example.skiSlope.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardServiceDefinition {

    Optional<Card> getCardById(Long id);

    List<Card> getAllCardsByUserId(Long userId);

    List<Card> getAllCards();

}
