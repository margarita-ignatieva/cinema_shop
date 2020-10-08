package com.cinema.shop.service.impl;

import com.cinema.shop.dao.UserDao;
import com.cinema.shop.lib.Inject;
import com.cinema.shop.lib.Service;
import com.cinema.shop.model.User;
import com.cinema.shop.service.UserService;
import com.cinema.shop.util.HashUtil;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        byte [] salt = HashUtil.getSalt();
        String hashedPassword = HashUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
