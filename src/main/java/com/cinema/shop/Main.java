package com.cinema.shop;

import com.cinema.shop.exceptions.AuthenticationException;
import com.cinema.shop.lib.Injector;
import com.cinema.shop.model.CinemaHall;
import com.cinema.shop.model.Movie;
import com.cinema.shop.model.MovieSession;
import com.cinema.shop.model.User;
import com.cinema.shop.service.AuthenticationService;
import com.cinema.shop.service.CinemaHallService;
import com.cinema.shop.service.MovieService;
import com.cinema.shop.service.MovieSessionService;
import com.cinema.shop.service.OrderService;
import com.cinema.shop.service.ShoppingCartService;
import com.cinema.shop.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.log4j.Logger;

public class Main {
    private static Injector injector = Injector.getInstance("com.cinema.shop");
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws AuthenticationException {
        Movie movie1 = new Movie();
        movie1.setTitle("Mad Max");
        movie1.setDescription("Cool men cool cars, sand everywhere");
        Movie movie2 = new Movie();
        movie2.setTitle("Terminator 2");
        movie2.setDescription("I AM BACK");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie1);
        movieService.add(movie2);
        movieService.getAll().forEach(log::info);

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
        cinemaHallService.getAll().forEach(log::info);

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
        availableSessions.forEach(log::info);

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        authenticationService.register("chester@mail.com", "1234f");
        log.info(authenticationService.login("chester@mail.com", "1234f"));

        User shinoda = new User("mike@mail.com", "123er");
        UserService userService = (UserService) injector.getInstance(UserService.class);
        userService.add(shinoda);
        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        shoppingCartService.registerNewShoppingCart(shinoda);
        shoppingCartService.addSession(movieSession, shinoda);
        log.info(shoppingCartService.getByUser(shinoda).getTickets());

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(shinoda).getTickets(), shinoda);
        log.info("Shinoda's orders: " + orderService.getOrderHistory(shinoda));
    }
}
