package com.cinema.shop.dao;

import com.cinema.shop.model.ShoppingCart;
import com.cinema.shop.model.User;

import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
