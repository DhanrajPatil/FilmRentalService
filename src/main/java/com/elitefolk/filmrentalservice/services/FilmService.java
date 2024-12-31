package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.models.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilmService {
    Page<Film> getAllFilms(Pageable pageable);
    List<Film> getFilmsByRating(String rating);
    Film getFilmById(Short id);
}
