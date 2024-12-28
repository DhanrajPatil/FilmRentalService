package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Film;
import com.elitefolk.filmrentalservice.models.Language;
import com.elitefolk.filmrentalservice.models.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
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
    private Date lastUpdate;

    public Film toFilm() {
        Language language = new Language();
        language.setId(languageId);
        language.setName(this.language);
        Language originalLanguage = new Language();
        originalLanguage.setId(originalLanguageId);
        originalLanguage.setName(this.originalLanguage);
        return new Film(id,
                title,
                description,
                releaseYear,
                language,
                originalLanguage,
                rentalDuration, rentalRate, length, replacementCost, Rating.valueOf(rating), specialFeatures, lastUpdate);
    }

    public static FilmDto fromFilm(Film film) {
        String language = film.getLanguage() != null ? film.getLanguage().getName() : null;
        String originalLanguage = film.getOriginalLanguage() != null ? film.getOriginalLanguage().getName() : null;
        Byte originalLanguageId = film.getOriginalLanguage() != null ? film.getOriginalLanguage().getId() : null;
        Byte languageId = film.getLanguage() != null ? film.getLanguage().getId() : null;
        return new FilmDto(
            film.getId(),
            film.getTitle(),
            film.getDescription(),
            film.getReleaseYear(),
            language,
            languageId,
            originalLanguage,
            originalLanguageId,
            film.getRentalDuration(),
            film.getRentalRate(),
            film.getLength(),
            film.getReplacementCost(),
            film.getRating() != null ? film.getRating().name() : null,
            film.getSpecialFeatures(),
            film.getLastUpdate()
        );
    }

    public static List<FilmDto> fromFilmsToDtoList(List<Film> films) {
        return films.stream().map(FilmDto::fromFilm).toList();
    }
}
