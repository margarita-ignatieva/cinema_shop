package com.cinema.shop.controllers;

import com.cinema.shop.mappers.CinemaHallMapper;
import com.cinema.shop.model.dto.CinemaHallRequestDto;
import com.cinema.shop.model.dto.CinemaHallResponseDto;
import com.cinema.shop.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public void create(@RequestBody CinemaHallRequestDto cinemaHallDto) {
        cinemaHallService.add(cinemaHallMapper.getFromDto(cinemaHallDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAll() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::getCinemaHallDto)
                .collect(Collectors.toList());
    }
}
