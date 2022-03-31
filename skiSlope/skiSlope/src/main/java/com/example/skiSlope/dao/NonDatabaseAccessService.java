package com.example.skiSlope.dao;

import com.example.skiSlope.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class NonDatabaseAccessService implements TicketDao{

    private static List<Ticket> DB = new ArrayList<>();

    @Override
    public int insertTicket(UUID code, Ticket ticket) {
        DB.add(new Ticket(code, ticket.getDiscountType(), ticket.getCost(), ticket.getLiftId(), ticket.getNumberOfEntries(), ticket.getCustomerId()));
        return 1;
    }

    public List<Ticket> selectAllTickets() {
        return DB;
    }

    @Override
    public Optional<Ticket> selectTicketByCode(UUID code) {
        return DB.stream()
                .filter(ticket -> ticket.getId().equals(code))
                .findFirst();
    }

    @Override
    public int deleteTicketByCode(UUID code) {
        Optional<Ticket> ticketOptional = selectTicketByCode(code);
        if(ticketOptional.isEmpty()){
            return 0;
        }
        DB.remove((ticketOptional.get()));
        return 1;
    }

    @Override
    public int updateTicketByCode(UUID code, Ticket update) {
        return selectTicketByCode(code)
                .map(ticket -> {
                    int indexOfTicketToUpdate = DB.indexOf(ticket);
                    if(indexOfTicketToUpdate >= 0){
                        DB.set(indexOfTicketToUpdate, new Ticket(code, update.getDiscountType(), update.getCost(), update.getLiftId(), update.getNumberOfEntries(), update.getCustomerId()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
