package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.dtos.BasicFilmDto;
import com.elitefolk.filmrentalservice.exceptions.FilmNotFoundException;
import com.elitefolk.filmrentalservice.models.Film;
import com.elitefolk.filmrentalservice.models.Language;
import com.elitefolk.filmrentalservice.repositories.FilmRepository;
import jakarta.transaction.Transactional;
import org.springframework.aop.framework.AopContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final LanguageService languageService;
    private final ActorService actorService;
    private final CategoryService categoryService;

    public FilmServiceImpl(FilmRepository filmRepository,
                           LanguageService languageService,
                           ActorService actorService,
                           CategoryService categoryService) {
        this.filmRepository = filmRepository;
        this.languageService = languageService;
        this.actorService = actorService;
        this.categoryService = categoryService;
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

    @Override
    @Transactional
    public Film saveFilm(Film film) {
        return getFilm(film, languageService, actorService, categoryService, filmRepository);
    }

    @Override
    @Transactional
    public Film updateFilm(Short id, Film film) {
        Film existingFilm = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("Film not found with id " + id, BasicFilmDto.fromFilm(film)));
        film.setId(existingFilm.getId());
        return getFilm(film, languageService, actorService, categoryService, filmRepository);
    }

    @Override
    @Transactional
    public void deleteFilm(Short id) {
        filmRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Film patchFilm(Short id, Film film) {
        Film existingFilm = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("Film not found with id " + id, BasicFilmDto.fromFilm(film)));
        if(film.getTitle() != null) {
            existingFilm.setTitle(film.getTitle());
        }
        if(film.getDescription() != null) {
            existingFilm.setDescription(film.getDescription());
        }
        if(film.getReleaseYear() != null) {
            existingFilm.setReleaseYear(film.getReleaseYear());
        }
        if(film.getLanguage() != null && (film.getLanguage().getId() != null || film.getLanguage().getName() != null)) {
            existingFilm.setLanguage(film.getLanguage());
        }
        if(film.getOriginalLanguage() != null && (film.getOriginalLanguage().getId() != null || film.getOriginalLanguage().getName() != null)) {
            existingFilm.setOriginalLanguage(film.getOriginalLanguage());
        }
        if(film.getRentalDuration() != null) {
            existingFilm.setRentalDuration(film.getRentalDuration());
        }
        if(film.getRentalRate() != null) {
            existingFilm.setRentalRate(film.getRentalRate());
        }
        if(film.getLength() != null) {
            existingFilm.setLength(film.getLength());
        }
        if(film.getReplacementCost() != null) {
            existingFilm.setReplacementCost(film.getReplacementCost());
        }
        if(film.getRating() != null) {
            existingFilm.setRating(film.getRating());
        }
        if(film.getSpecialFeatures() != null) {
            existingFilm.setSpecialFeatures(film.getSpecialFeatures());
        }
        if(film.getActors() != null && !film.getActors().isEmpty()) {
            existingFilm.setActors(film.getActors());
        }
        if(film.getCategories() != null && !film.getCategories().isEmpty()) {
            existingFilm.setCategories(film.getCategories());
        }
        return getFilm(existingFilm, languageService, actorService, categoryService, filmRepository);
    }

    private static Film getFilm(Film film, LanguageService languageService, ActorService actorService, CategoryService categoryService, FilmRepository filmRepository) {
        List<Language> languages = new ArrayList<>();
        languages.add(film.getLanguage());
        languages.add(film.getOriginalLanguage());
        languages = languageService.fetchLanguageByNameOrId(languages);
        if (!languages.isEmpty()) {
            film.setLanguage(languages.get(0));
        }
        if (languages.size() > 1) {
            film.setOriginalLanguage(languages.get(1));
        }
        if(film.getActors() != null && !film.getActors().isEmpty()) {
            film.setActors(actorService.fetchOrCreateActors(film.getActors()));
        }
        if(film.getCategories() != null && !film.getCategories().isEmpty()) {
            film.setCategories(categoryService.fetchOrCreateCategories(film.getCategories()));
        }
        return filmRepository.save(film);
    }
}
