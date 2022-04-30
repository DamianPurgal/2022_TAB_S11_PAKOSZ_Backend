package com.example.skiSlope.service.implementations;

import com.example.skiSlope.dao.TicketDao;
import com.example.skiSlope.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketDao ticketDao;

    @Autowired
    public TicketService(@Qualifier("fakeDao") TicketDao ticketDao) {this.ticketDao = ticketDao;}

    public int addTicket(Ticket ticket){return ticketDao.insertTicket(ticket);}

    public List<Ticket> getAllTickets(){return ticketDao.selectAllTickets();}

    public Optional<Ticket> getTicketById(UUID code){return  ticketDao.selectTicketByCode(code);}

    public int deleteTicket(UUID code){return ticketDao.deleteTicketByCode(code);}

    public int updateTicket(UUID code, Ticket newTicket){return ticketDao.updateTicketByCode(code, newTicket);}



}
