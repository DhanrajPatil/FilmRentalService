package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.models.Film;
import com.elitefolk.filmrentalservice.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getAllFilms() {
        List<Film> films = filmRepository.findAll();
        return films;
    }

    @Override
    public List<Film> getFilmsByRating(String rating) {
        return filmRepository.findByRating(rating);
    }

    @Override
    public Film getFilmById(Short id) {
        return filmRepository.findById(id).orElse(null);
    }
}
