package com.cinema.shop;

import com.cinema.shop.lib.Injector;
import com.cinema.shop.model.Movie;
import com.cinema.shop.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("com.cinema.shop");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Mad Max");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
