package com.cinema.shop.service.impl;

import com.cinema.shop.dao.CinemaHallDao;
import com.cinema.shop.lib.Inject;
import com.cinema.shop.lib.Service;
import com.cinema.shop.model.CinemaHall;
import com.cinema.shop.service.CinemaHallService;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
