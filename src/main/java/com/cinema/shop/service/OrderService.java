package com.cinema.shop.service;

import com.cinema.shop.model.Order;
import com.cinema.shop.model.Ticket;
import com.cinema.shop.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
