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
public class BasicActorDto{
    private Short id;
    private String firstName;
    private String lastName;

    public BasicActorDto(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
    }

    public Actor toActor() {
        return new Actor(id, firstName, lastName, new ArrayList<>(), null);
    }

    public static BasicActorDto fromActor(Actor actor) {
        return new BasicActorDto(actor);
    }

    public static List<? extends BasicActorDto> fromActors(List<Actor> actors) {
        return actors.stream().map(BasicActorDto::fromActor).toList();
    }
}
