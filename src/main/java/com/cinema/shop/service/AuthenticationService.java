package com.cinema.shop.service;

import com.cinema.shop.exceptions.AuthenticationException;
import com.cinema.shop.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
