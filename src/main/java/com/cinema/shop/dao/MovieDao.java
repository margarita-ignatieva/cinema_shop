package com.cinema.shop.dao;

import com.cinema.shop.model.Movie;
import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie getById(Long id);
}
