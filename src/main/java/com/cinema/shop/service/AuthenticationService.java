package com.cinema.shop.service;

import com.cinema.shop.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
