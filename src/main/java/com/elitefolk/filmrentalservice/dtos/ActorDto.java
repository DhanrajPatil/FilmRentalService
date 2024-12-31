package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Actor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDto {
    private Short id;
    private String firstName;
    private String lastName;
    private List<FilmDto> films;

    public ActorDto(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.films = actor.getMovies() != null && !actor.getMovies().isEmpty() ?
                FilmDto.fromFilmsToDtoList(actor.getMovies(), false) : new ArrayList<>();
    }

    public Actor toActor() {
        return new Actor(id, firstName, lastName, new ArrayList<>(), null);
    }

    public static ActorDto fromActor(Actor actor) {
        return new ActorDto(actor);
    }

    public static List<ActorDto> fromActors(List<Actor> actors) {
        return actors.stream().map(ActorDto::fromActor).toList();
    }
}
