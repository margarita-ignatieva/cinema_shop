package com.cinema.shop.dao;

import com.cinema.shop.model.User;

public interface UserDao {
    User add(User user);

    User findByEmail(String email);
}
