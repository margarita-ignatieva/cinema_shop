package com.cinema.shop.model.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private List<Long> ticketIds;
    private String orderData;
    private Long userId;
}
