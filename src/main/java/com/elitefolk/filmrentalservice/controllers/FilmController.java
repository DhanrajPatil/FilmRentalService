package com.elitefolk.filmrentalservice.controllers;

import com.elitefolk.filmrentalservice.dtos.FilmDto;
import com.elitefolk.filmrentalservice.dtos.PaginatedResponse;
import com.elitefolk.filmrentalservice.models.Film;
import com.elitefolk.filmrentalservice.services.FilmService;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public PaginatedResponse<FilmDto> getFilms(Pageable pageable) {
        Page<Film> films = filmService.getAllFilms(pageable);
        Page<FilmDto> pageDtos = films.map(FilmDto::new);
        return new PaginatedResponse<>(pageDtos);
    }

    @GetMapping("/rating/{rating}")
    public List<FilmDto> getFilmsByRating(@PathVariable String rating) {
        return FilmDto.fromFilmsToDtoList(filmService.getFilmsByRating(rating), false);
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable Short id) {
        return filmService.getFilmById(id);
        //return new FilmDto(filmService.getFilmById(id), true);
    }
}
