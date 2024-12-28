package com.elitefolk.filmrentalservice.dtos;


import com.elitefolk.filmrentalservice.models.Actor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDto {
    private Short id;
    private String firstName;
    private String lastName;

    public Actor toActor() {
        return new Actor(id, firstName, lastName, null);
    }

    public static ActorDto fromActor(Actor actor) {
        return new ActorDto(actor.getId(), actor.getFirstName(), actor.getLastName());
    }

    public static List<ActorDto> fromActors(List<Actor> actors) {
        return actors.stream().map(ActorDto::fromActor).toList();
    }
}
