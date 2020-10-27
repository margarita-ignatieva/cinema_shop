package com.cinema.shop.service.impl;

import com.cinema.shop.dao.TicketDao;
import com.cinema.shop.model.Ticket;
import com.cinema.shop.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketDao ticketDao;

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Ticket add(Ticket ticket) {
        return ticketDao.add(ticket);
    }

    @Override
    public Ticket getById(Long id) {
        return ticketDao.getById(id);
    }
}
