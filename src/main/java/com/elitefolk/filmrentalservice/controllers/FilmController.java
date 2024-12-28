package com.elitefolk.filmrentalservice.controllers;

import com.elitefolk.filmrentalservice.dtos.FilmDto;
import com.elitefolk.filmrentalservice.models.Film;
import com.elitefolk.filmrentalservice.services.FilmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<FilmDto> getFilms() {
        List<Film> films = filmService.getAllFilms();
        return FilmDto.fromFilmsToDtoList(films);
    }

    @GetMapping("/rating/{rating}")
    public List<Film> getFilmsByRating(@PathVariable String rating) {
        return filmService.getFilmsByRating(rating);
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable Short id) {
        return filmService.getFilmById(id);
    }
}
