package com.cinema.shop.service.impl;

import com.cinema.shop.dao.MovieSessionDao;
import com.cinema.shop.dao.OrderDao;
import com.cinema.shop.lib.Inject;
import com.cinema.shop.lib.Service;
import com.cinema.shop.model.Order;
import com.cinema.shop.model.Ticket;
import com.cinema.shop.model.User;
import com.cinema.shop.service.OrderService;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        return null;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
