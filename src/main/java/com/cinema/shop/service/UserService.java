package com.cinema.shop.service;

import com.cinema.shop.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
