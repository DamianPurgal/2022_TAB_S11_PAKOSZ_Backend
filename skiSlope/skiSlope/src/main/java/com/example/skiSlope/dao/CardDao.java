package com.example.skiSlope.dao;

import com.example.skiSlope.model.Card;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardDao {

//    int insertCard(UUID code, Card card);
//
//    default int insertCard(Card card){
//        UUID code = UUID.randomUUID();
//        return insertCard(code, card);
//    }

    List<Card> selectAllCards();

    Optional<Card> selectCardByCode(UUID code);

    int deleteCardByCode(UUID code);
//    int updateCardByCode(UUID code, Card card);
}
