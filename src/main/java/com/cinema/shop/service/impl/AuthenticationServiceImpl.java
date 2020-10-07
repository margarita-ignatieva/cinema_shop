package com.cinema.shop.service.impl;

import com.cinema.shop.exceptions.AuthenticationException;
import com.cinema.shop.lib.Inject;
import com.cinema.shop.lib.Service;
import com.cinema.shop.model.User;
import com.cinema.shop.service.AuthenticationService;
import com.cinema.shop.service.UserService;
import com.cinema.shop.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isPresent() && isValid(password, userFromDb.get())) {
            return userFromDb.get();
        }
        throw new AuthenticationException("Incorrect email or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        user = userService.add(user);
        return user;
    }

    private boolean isValid(String password, User user) {
        return HashUtil.hashPassword(password, user.getSalt()).equals(user.getPassword());
    }
}
