package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.models.Film;

import java.util.List;

public interface FilmService {
    List<Film> getAllFilms();
    List<Film> getFilmsByRating(String rating);
    Film getFilmById(Short id);
}
