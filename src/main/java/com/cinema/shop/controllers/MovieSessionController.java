package com.cinema.shop.controllers;

import com.cinema.shop.mappers.MovieSessionMapper;
import com.cinema.shop.model.dto.MovieSessionRequestDto;
import com.cinema.shop.model.dto.MovieSessionResponseDto;
import com.cinema.shop.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieSessionController {

    @Autowired
    private MovieSessionService movieSessionService;

    @Autowired
    protected MovieSessionMapper movieSessionMapper;

    @PostMapping("/movie-sessions")
    public void addMovieSessionDto(@RequestBody MovieSessionRequestDto requestDto) {
        movieSessionService.add(movieSessionMapper.getMovieSession(requestDto));
    }

    @GetMapping("/movie-sessions/available")
    public List<MovieSessionResponseDto> getAll(@RequestParam(name = "movieId") Long id,
                                                @RequestParam(name = "date") LocalDate date) {
        return  movieSessionService.findAvailableSessions(id, date)
                .stream().map(movieSessionMapper::getMovieSessionDto)
                .collect(Collectors.toList());
    }
}
