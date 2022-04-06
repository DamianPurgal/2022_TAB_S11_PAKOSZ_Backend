package com.example.skiSlope.dao;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class NonDatabaseTicketAccessService implements TicketDao, CardDao{

    private static List<Ticket> tickets = new ArrayList<>();
    private static List<Card> cards = new ArrayList<>();

    @Override
    public int insertTicket(UUID code, Ticket ticket) {
        Ticket newTicket = new Ticket(ticket.getId(), code, ticket.getDiscountType(), ticket.getCost(), ticket.getLiftId(), ticket.getNumberOfEntries(), ticket.getCustomerId());
        tickets.add(newTicket);
        cards.add(newTicket);
        return 1;
    }

    public List<Ticket> selectAllTickets() {
        return tickets;
    }

    @Override
    public Optional<Ticket> selectTicketByCode(UUID code) {
        return tickets.stream()
                .filter(ticket -> ticket.getId().equals(code))
                .findFirst();
    }

    @Override
    public int deleteTicketByCode(UUID code) {
        Optional<Ticket> ticketOptional = selectTicketByCode(code);
        if(ticketOptional.isEmpty()){
            return 0;
        }
        tickets.remove(ticketOptional.get());
        deleteCardByCode(code);
        return 1;
    }

    @Override
    public int updateTicketByCode(UUID code, Ticket update) {
        return selectTicketByCode(code)
                .map(ticket -> {
                    int indexOfTicketToUpdate = tickets.indexOf(ticket);
                    int indexOfCardToUpdate = cards.indexOf(ticket);
                    Ticket newTicket = new Ticket(update.getId(), code, update.getDiscountType(), update.getCost(), update.getLiftId(), update.getNumberOfEntries(), update.getCustomerId());
                    if(indexOfTicketToUpdate >= 0){
                        tickets.set(indexOfTicketToUpdate, newTicket);
                        cards.set(indexOfCardToUpdate, newTicket);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public List<Card> selectAllCards() {
        return cards;
    }

    @Override
    public Optional<Card> selectCardByCode(UUID code) {
        return cards.stream()
                .filter(card -> card.getId().equals(code))
                .findFirst();
    }

    @Override
    public int deleteCardByCode(UUID code) {
        Optional<Card> cardOptional = selectCardByCode(code);
        if(cardOptional.isEmpty()){
            return 0;
        }
        tickets.remove(cardOptional.get());
        return 1;
    }

//    @Override
//    public int updateCardByCode(UUID code, Card update) {
//        return selectCardByCode(code)
//                .map(card -> {
//                    int indexOfCardToUpdate = tickets.indexOf(card);
//                    if(indexOfCardToUpdate >= 0){
//                        tickets.set(indexOfCardToUpdate, new Car(update.getId(), code, update.getDiscountType(), update.getCost(), update.getLiftId(), update.getNumberOfEntries(), update.getCustomerId()));
//                        return 1;
//                    }
//                    return 0;
//                })
//                .orElse(0);
//    }
}
