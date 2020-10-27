package com.cinema.shop.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private List<Long> ticketIds;
    private String orderData;
    private Long userId;
}
