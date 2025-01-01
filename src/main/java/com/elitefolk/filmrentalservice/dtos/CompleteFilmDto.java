package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteFilmDto extends BasicFilmDto{
    List<BasicActorDto> actors;
    List<BasicCategoryDto> categories;

    public CompleteFilmDto(Film film) {
        super(film);
        this.actors = film.getActors() != null ? (List<BasicActorDto>) BasicActorDto.fromActors(film.getActors()) : new ArrayList<>();
        this.categories = film.getCategories() != null ? BasicCategoryDto.fromCategories(film.getCategories()) : new ArrayList<>();
    }

    public Film toFilm() {
        Film film = super.toFilm();
        film.setActors(actors != null ? actors.stream().map(BasicActorDto::toActor).toList() : new ArrayList<>());
        film.setCategories(categories != null ? categories.stream().map(BasicCategoryDto::toCategory).toList() : new ArrayList<>());
        return film;
    }

    public static CompleteFilmDto fromFilm(Film film) {
        return new CompleteFilmDto(film);
    }

    public static List<CompleteFilmDto> fromFilmsToCompleteDto(List<Film> films) {
        return films.stream().map(CompleteFilmDto::fromFilm).toList();
    }
}
