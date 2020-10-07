package com.cinema.shop;

import com.cinema.shop.lib.Injector;
import com.cinema.shop.model.CinemaHall;
import com.cinema.shop.model.Movie;
import com.cinema.shop.model.MovieSession;
import com.cinema.shop.service.CinemaHallService;
import com.cinema.shop.service.MovieService;
import com.cinema.shop.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("com.cinema.shop");

    public static void main(String[] args) {
        Movie movie1 = new Movie();
        movie1.setTitle("Mad Max");
        movie1.setDescription("Cool men cool cars, sand everywhere");
        Movie movie2 = new Movie();
        movie2.setTitle("Terminator 2");
        movie2.setDescription("I AM BACK");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie1);
        movieService.add(movie2);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(45);
        cinemaHall1.setDescription("5D is here");
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(80);
        cinemaHall2.setDescription("IMAX has never ever been closer");
        CinemaHallService cinemaHallService =
                (CinemaHallService)injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall1);
        cinemaHallService.add(cinemaHall2);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie1);
        movieSession.setCinemaHall(cinemaHall1);
        movieSession.setShowTime(LocalDateTime.now().plusDays(3));
        MovieSession movieSession2 = new MovieSession();
        movieSession2.setMovie(movie2);
        movieSession2.setCinemaHall(cinemaHall2);
        movieSession2.setShowTime(LocalDateTime.now());
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.add(movieSession2);

        List<MovieSession> availableSessions =
                movieSessionService.findAvailableSessions(movie2.getId(), LocalDate.now());
        availableSessions.forEach(System.out::println);
    }
}
