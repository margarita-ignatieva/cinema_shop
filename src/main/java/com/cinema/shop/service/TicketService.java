package com.cinema.shop.service;

import com.cinema.shop.model.Ticket;

public interface TicketService {
    Ticket add(Ticket ticket);

    Ticket getById(Long id);
}
