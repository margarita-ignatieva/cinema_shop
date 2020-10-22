package com.cinema.shop.mappers;

import com.cinema.shop.model.MovieSession;
import com.cinema.shop.model.dto.MovieSessionRequestDto;
import com.cinema.shop.model.dto.MovieSessionResponseDto;
import com.cinema.shop.service.CinemaHallService;
import com.cinema.shop.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaHallService cinemaHallService;

    public MovieSession getMovieSession(MovieSessionRequestDto requestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHallService.getById(requestDto.getCinemaHallId()));
        movieSession.setMovie(movieService.getById(requestDto.getMovieId()));
        movieSession.setShowTime(LocalDateTime.parse(requestDto.getShowTime()));
        return movieSession;
    }

    public MovieSessionResponseDto getMovieSessionDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setId(movieSession.getId());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return movieSessionResponseDto;
    }
}
