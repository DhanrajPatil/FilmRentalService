package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Actor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteActorDto extends BasicActorDto {
    private List<BasicFilmDto> films;

    public CompleteActorDto(Actor actor) {
        super(actor);
        this.films = (List<BasicFilmDto>) BasicFilmDto.fromFilms(actor.getMovies());
    }

    public static CompleteActorDto fromActor(Actor actor) {
        return new CompleteActorDto(actor);
    }

    public static List<CompleteActorDto> fromActors(List<Actor> actors) {
        return actors.stream().map(CompleteActorDto::fromActor).toList();
    }
}
