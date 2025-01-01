package com.elitefolk.filmrentalservice.controllers;

import com.elitefolk.filmrentalservice.dtos.BasicFilmDto;
import com.elitefolk.filmrentalservice.dtos.CompleteFilmDto;
import com.elitefolk.filmrentalservice.dtos.PaginatedResponse;
import com.elitefolk.filmrentalservice.models.Film;
import com.elitefolk.filmrentalservice.services.FilmService;
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
    public PaginatedResponse<BasicFilmDto> getFilms(Pageable pageable) {
        Page<Film> films = filmService.getAllFilms(pageable);
        Page<BasicFilmDto> pageDtos = films.map(BasicFilmDto::new);
        return new PaginatedResponse<>(pageDtos);
    }

    @GetMapping("/rating/{rating}")
    public List<BasicFilmDto> getFilmsByRating(@PathVariable String rating) {
        return BasicFilmDto.fromFilms(filmService.getFilmsByRating(rating));
    }

    @GetMapping("/{id}")
    public CompleteFilmDto getFilmById(@PathVariable Short id) {
        //return filmService.getFilmById(id);
        return new CompleteFilmDto(filmService.getFilmById(id));
    }

    @PostMapping
    public CompleteFilmDto saveFilm(@RequestBody CompleteFilmDto filmDto) {
        Film film = filmDto.toFilm();
        return new CompleteFilmDto(filmService.saveFilm(film));
    }

    @PutMapping("/{id}")
    public CompleteFilmDto updateFilm(@PathVariable Short id, @RequestBody CompleteFilmDto filmDto) {
        Film film = filmDto.toFilm();
        return new CompleteFilmDto(filmService.updateFilm(id, film));
    }

    @PatchMapping("/{id}")
    public CompleteFilmDto patchFilm(@PathVariable Short id, @RequestBody CompleteFilmDto filmDto) {
        Film film = filmDto.toFilm();
        return new CompleteFilmDto(filmService.patchFilm(id, film));
    }
}
