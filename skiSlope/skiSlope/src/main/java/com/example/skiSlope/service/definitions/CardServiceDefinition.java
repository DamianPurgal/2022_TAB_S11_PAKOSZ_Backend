package com.example.skiSlope.service.definitions;

import com.example.skiSlope.model.Card;

import java.util.List;

public interface CardServiceDefinition {

    Card getCardById(Long id);

    List<Card> getAllCardsByUserId(Long userId);

    List<Card> getAllCards();

}
