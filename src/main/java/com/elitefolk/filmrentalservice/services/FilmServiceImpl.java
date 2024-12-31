package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.models.Film;
import com.elitefolk.filmrentalservice.repositories.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Page<Film> getAllFilms(Pageable pageable) {
        Page<Film> films = filmRepository.findAll(pageable);
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
