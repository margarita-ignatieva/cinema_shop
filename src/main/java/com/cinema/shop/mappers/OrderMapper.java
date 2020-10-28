package com.cinema.shop.mappers;

import com.cinema.shop.model.Order;
import com.cinema.shop.model.dto.OrderResponseDto;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponseDto getOrderDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTicketIds(order.getTickets().stream()
                .map(ticket -> ticket.getId())
                .collect(Collectors.toList()));
        orderResponseDto.setOrderDate(order.getOrderDate()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }
}
