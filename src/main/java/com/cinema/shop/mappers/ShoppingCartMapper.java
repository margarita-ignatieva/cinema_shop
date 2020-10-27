package com.cinema.shop.mappers;

import com.cinema.shop.model.ShoppingCart;
import com.cinema.shop.model.dto.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {

    public ShoppingCartResponseDto getShoppingCartDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto =
                new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketIds(shoppingCart.getTickets().stream()
                .map(ticket -> ticket.getId())
                .collect(Collectors.toList()));
        return shoppingCartResponseDto;
    }
}
