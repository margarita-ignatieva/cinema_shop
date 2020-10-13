package com.cinema.shop.dao;

import com.cinema.shop.model.Order;
import com.cinema.shop.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrderHistory(User user);
}
