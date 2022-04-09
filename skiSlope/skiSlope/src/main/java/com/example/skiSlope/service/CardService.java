package com.example.skiSlope.service;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.Voucher;
import com.example.skiSlope.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CardService implements CardServiceDefinition{

    private CardRepository cardRepository;

    @Override
    public Optional<Card> getCardById(Long id) {
        return Optional.of(cardRepository.getById(id));
    }

    @Override
    public List<Card> getAllCardsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }
//    @Override
//    public Optional<Ticket> getCardById(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Card> getAllCardsByUserId(Long userId) {
//        return null;
//    }
//
//    @Override
//    public List<Card> getAllCards() {
//        return null;
//    }

//    CardRepository cardRepository;
//
//    @Override
//    public Optional<Ticket> getCardById(Long id) {
//        return cardRepository.findById(id);
//    }
//
//    @Override
//    public List<Card> getAllCardsByUserId(Long userId) {
//        return null;
//    }
//
//    @Override
//    public List<Card> getAllCards() {
//        return null;
//    }

//    Card addCard(Card card);
//
//    Card getCardById(Long id);
//
//    List<Card> getAllCardsByUserId(Long userId);
//
//    List<Card> getAllCards();
//
//    void updateCardsData(Card card, Long id);
//
//    void deleteCard(Long id);
//
//    /*private final CardDao cardDao;
//
//    public CardService(@Qualifier("fakeDao") CardDao cardDao) {
//        this.cardDao = cardDao;
//    }
//
//    public List<Card> getAllCards(){return cardDao.selectAllCards();}
//
//    public Optional<Card> getCardById(UUID code){return  cardDao.selectCardByCode(code);}
//
//    //public int deleteTicket(UUID code){return cardDao.deleteCardByCode(code);}*/
}
