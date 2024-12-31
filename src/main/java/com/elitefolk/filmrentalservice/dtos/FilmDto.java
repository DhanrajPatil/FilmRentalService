package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Actor;
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
    private Timestamp lastUpdate;
    private List<ActorsJson> actors;

    public FilmDto(Film film){
        this(film, true);
    }

    public FilmDto(Film film, boolean includeActors) {
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
        this.actors = film.getActors() != null && includeActors ? ActorsJson.fromActors(film.getActors()) : new ArrayList<>();
    }

    public Film toFilm() {
        Language language = new Language();
        language.setId(languageId);
        language.setName(this.language);
        Language originalLanguage = new Language();
        originalLanguage.setId(originalLanguageId);
        originalLanguage.setName(this.originalLanguage);
        return new Film(
            id,
            title,
            description,
            releaseYear,
            language,
            originalLanguage,
            rentalDuration,
            rentalRate,
            length,
            replacementCost,
            Rating.valueOf(rating),
            specialFeatures,
            lastUpdate,
            new ArrayList<>(),
            new ArrayList<>()
        );
    }

    public static FilmDto fromFilm(Film film) {
        return new FilmDto(film, true);
    }

    public static List<FilmDto> fromFilmsToDtoList(List<Film> films, boolean includeActors) {
        return films.stream().map((film) -> new FilmDto(film, includeActors)).toList();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ActorsJson {
        private Short id;
        private String firstName;
        private String lastName;

        public ActorsJson(Actor actor) {
            this.id = actor.getId();
            this.firstName = actor.getFirstName();
            this.lastName = actor.getLastName();
        }

        public static List<ActorsJson> fromActors(List<Actor> actors) {
            return actors.stream().map(ActorsJson::new).toList();
        }
    }
}
