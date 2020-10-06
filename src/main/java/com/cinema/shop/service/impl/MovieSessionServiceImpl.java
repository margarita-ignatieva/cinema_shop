package com.cinema.shop.service.impl;

import com.cinema.shop.dao.MovieSessionDao;
import com.cinema.shop.lib.Inject;
import com.cinema.shop.lib.Service;
import com.cinema.shop.model.MovieSession;
import com.cinema.shop.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }
}
