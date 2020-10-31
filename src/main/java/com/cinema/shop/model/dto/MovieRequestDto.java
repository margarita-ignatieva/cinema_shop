package com.cinema.shop.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class MovieRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String description;
}
