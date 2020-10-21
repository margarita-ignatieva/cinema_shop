package com.cinema.shop;

import com.cinema.shop.config.AppConfig;
import com.cinema.shop.exceptions.AuthenticationException;
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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    private static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private static final UserService userService = context.getBean(UserService.class);
    private static final MovieService movieService = context.getBean(MovieService.class);
    private static final CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
    private static final MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
    private static final AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
    private static final ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
    private static final OrderService orderService = context.getBean(OrderService.class);


    public static void main(String[] args) {

        Movie movie1 = new Movie();
        movie1.setTitle("Mad Max");
        movie1.setDescription("Cool men cool cars, sand everywhere");
        Movie movie2 = new Movie();
        movie2.setTitle("Terminator 2");
        movie2.setDescription("I AM BACK");
        movieService.add(movie1);
        movieService.add(movie2);
        movieService.getAll().forEach(log::info);

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(45);
        cinemaHall1.setDescription("5D is here");
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(80);
        cinemaHall2.setDescription("IMAX has never ever been closer");
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
        movieSessionService.add(movieSession);
        movieSessionService.add(movieSession2);

        List<MovieSession> availableSessions =
                movieSessionService.findAvailableSessions(movie2.getId(), LocalDate.now());
        availableSessions.forEach(log::info);

        authenticationService.register("chester@mail.com", "1234f");
        try {
            authenticationService.login("chester@mail.com", "1234f");
            log.info("A user logged in.");
        } catch (AuthenticationException e) {
            log.warn("User failed to log in. AuthenticationException: ", e);
        }

        User shinoda = new User("mike@mail.com", "123er");
        userService.add(shinoda);
        shoppingCartService.registerNewShoppingCart(shinoda);
        shoppingCartService.addSession(movieSession, shinoda);
        log.info("Trying to get user shopping cart:"
                + shoppingCartService.getByUser(shinoda).getTickets());

        orderService.completeOrder(shoppingCartService.getByUser(shinoda).getTickets(), shinoda);
        log.info("Trying to get user orders: " + orderService.getOrderHistory(shinoda));
    }
}
