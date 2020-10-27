package com.cinema.shop.mappers;

import com.cinema.shop.model.ShoppingCart;
import com.cinema.shop.model.dto.ShoppingCartResponseDto;
import com.cinema.shop.service.UserService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private UserService userService;

    public ShoppingCartMapper(UserService userService) {
        this.userService = userService;
    }

    public ShoppingCartResponseDto getShoppingCartDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto =
                new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketIds(shoppingCart.getTickets().stream()
                .map(x -> x.getId())
                .collect(Collectors.toList()));
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());
        return shoppingCartResponseDto;
    }
}
