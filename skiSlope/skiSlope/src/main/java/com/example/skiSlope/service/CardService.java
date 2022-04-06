package com.example.skiSlope.service;

import com.example.skiSlope.dao.CardDao;
import com.example.skiSlope.model.Card;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    private final CardDao cardDao;

    public CardService(@Qualifier("fakeDao") CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public List<Card> getAllCards(){return cardDao.selectAllCards();}

    public Optional<Card> getCardById(UUID code){return  cardDao.selectCardByCode(code);}

    public int deleteTicket(UUID code){return cardDao.deleteCardByCode(code);}
}
