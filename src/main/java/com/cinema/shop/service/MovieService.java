package com.cinema.shop.service;

import com.cinema.shop.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie getById(Long id);
}
