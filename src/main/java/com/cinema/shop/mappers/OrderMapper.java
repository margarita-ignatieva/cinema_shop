package com.cinema.shop.mappers;

import com.cinema.shop.model.Order;
import com.cinema.shop.model.dto.OrderResponseDto;
import com.cinema.shop.service.UserService;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private UserService userService;

    public OrderMapper(UserService userService) {
        this.userService = userService;
    }

    public OrderResponseDto getOrderDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTicketIds(order.getTickets().stream()
                .map(x -> x.getId())
                .collect(Collectors.toList()));
        orderResponseDto.setOrderData(order.getOrderDate()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }
}
