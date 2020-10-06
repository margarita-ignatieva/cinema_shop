package com.cinema.shop.dao;

import com.cinema.shop.model.CinemaHall;

import java.util.List;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
