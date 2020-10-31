package com.cinema.shop.controllers;

import com.cinema.shop.mappers.ShoppingCartMapper;
import com.cinema.shop.model.User;
import com.cinema.shop.model.dto.ShoppingCartResponseDto;
import com.cinema.shop.service.MovieSessionService;
import com.cinema.shop.service.ShoppingCartService;
import com.cinema.shop.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService, ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication authentication, @RequestParam Long movieSessionId) {
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.findByEmail(email).get();
        shoppingCartService.addSession(movieSessionService.getById(movieSessionId), user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        return shoppingCartMapper.getShoppingCartDto(
                shoppingCartService.getByUser(userService.findByEmail(email).get()));
    }
}
