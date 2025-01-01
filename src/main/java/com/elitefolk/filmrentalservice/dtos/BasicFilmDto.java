package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Film;
import com.elitefolk.filmrentalservice.models.Language;
import com.elitefolk.filmrentalservice.models.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicFilmDto {
    private Short id;
    private String title;
    private String description;
    private Integer releaseYear;
    private String language;
    private Byte languageId;
    private String originalLanguage;
    private Byte originalLanguageId;
    private Integer rentalDuration;
    private Double rentalRate;
    private Integer length;
    private Double replacementCost;
    private String rating;
    private String specialFeatures;
    private Timestamp lastUpdate;

    public BasicFilmDto(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.description = film.getDescription();
        this.releaseYear = film.getReleaseYear();
        this.language = film.getLanguage() != null ? film.getLanguage().getName() : null;
        this.languageId = film.getLanguage() != null ? film.getLanguage().getId() : null;
        this.originalLanguage = film.getOriginalLanguage() != null ? film.getOriginalLanguage().getName() : null;
        this.originalLanguageId = film.getOriginalLanguage() != null ? film.getOriginalLanguage().getId() : null;
        this.rentalDuration = film.getRentalDuration();
        this.rentalRate = film.getRentalRate();
        this.length = film.getLength();
        this.replacementCost = film.getReplacementCost();
        this.rating = film.getRating() != null ? film.getRating().name() : null;
        this.specialFeatures = film.getSpecialFeatures();
        this.lastUpdate = film.getLastUpdate();
    }

    public Film toFilm() {
        Language languageObj = new Language();
        languageObj.setId(languageId);
        languageObj.setName(language);
        Language originalLanguageObj = new Language();
        originalLanguageObj.setId(originalLanguageId);
        originalLanguageObj.setName(originalLanguage);
        return new Film(
                id,
                title,
                description,
                releaseYear,
                languageObj,
                originalLanguageObj,
                rentalDuration,
                rentalRate,
                length,
                replacementCost,
                rating != null ? Rating.valueOf(rating) : null,
                specialFeatures,
                lastUpdate,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public static BasicFilmDto fromFilm(Film film) {
        return new BasicFilmDto(film);
    }

    public static List<BasicFilmDto> fromFilms(List<Film> films) {
        return films.stream().map(BasicFilmDto::new).toList();
    }
}
