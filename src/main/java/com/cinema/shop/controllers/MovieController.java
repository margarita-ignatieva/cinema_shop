package com.cinema.shop.controllers;

import com.cinema.shop.mappers.MovieMapper;
import com.cinema.shop.model.dto.MovieRequestDto;
import com.cinema.shop.model.dto.MovieResponseDto;
import com.cinema.shop.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapper movieMapper;

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto movieRequestDto){
        movieService.add(movieMapper.getMovie(movieRequestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll().stream()
                .map(movieMapper::getMovieDto)
                .collect(Collectors.toList());
    }
}
