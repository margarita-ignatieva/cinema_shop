package com.cinema.shop.dao;

import com.cinema.shop.model.User;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);

    User getById(Long id);
}
