package com.example.skiSlope.dao;

import com.example.skiSlope.model.Card;
import com.example.skiSlope.model.Ticket;
import com.example.skiSlope.model.Voucher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class NonDatabaseTicketAccessService implements TicketDao, CardDao, VoucherDao{

    private static List<Ticket> tickets = new ArrayList<>();
    private static List<Card> cards = new ArrayList<>();
    private static List<Voucher> vouchers = new ArrayList<>();

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

    @Override
    public int insertVoucher(UUID code, Voucher voucher) {
        Voucher newVoucher = new Voucher(voucher.getId(), code, voucher.getDiscountType(), voucher.getCost(), voucher.getStartDate(), voucher.getExpireDate(), voucher.getCustomerId());
        vouchers.add(newVoucher);
        cards.add(newVoucher);
        return 1;
    }

    @Override
    public List<Voucher> selectAllVouchers() {
        return vouchers;
    }

    @Override
    public Optional<Voucher> selectVoucherByCode(UUID code) {
        return vouchers.stream()
                .filter(voucher -> voucher.getId().equals(code))
                .findFirst();
    }

    @Override
    public int deleteVoucherByCode(UUID code) {
        Optional<Voucher> voucherOptional = selectVoucherByCode(code);
        if(voucherOptional.isEmpty()){
            return 0;
        }
        vouchers.remove(voucherOptional.get());
        deleteCardByCode(code);
        return 1;
    }

    @Override
    public int updateVoucherByCode(UUID code, Voucher update) {
        return selectVoucherByCode(code)
                .map(voucher -> {
                    int indexOfVoucherToUpdate = vouchers.indexOf(voucher);
                    int indexOfCardToUpdate = cards.indexOf(voucher);
                    Voucher newVoucher = new Voucher(update.getId(), code, update.getDiscountType(), update.getCost(), update.getStartDate(), update.getExpireDate(), update.getCustomerId());
                    if(indexOfVoucherToUpdate >= 0){
                        vouchers.set(indexOfVoucherToUpdate, newVoucher);
                        cards.set(indexOfCardToUpdate, newVoucher);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
