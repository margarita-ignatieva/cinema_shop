package com.cinema.shop.model.dto;

import lombok.Data;

@Data
public class MovieSessionRequestDto {
    private Long movieId;
    private Long cinemaHallId;
    private String showTime;

}
