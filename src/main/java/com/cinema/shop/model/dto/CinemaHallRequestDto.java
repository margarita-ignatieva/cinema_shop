package com.cinema.shop.model.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @Min(10)
    private int capacity;
    @NotNull
    private String description;
}
