package com.cinema.shop.service;

import com.cinema.shop.model.MovieSession;
import com.cinema.shop.model.ShoppingCart;
import com.cinema.shop.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
