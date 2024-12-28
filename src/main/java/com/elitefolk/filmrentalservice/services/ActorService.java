package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.models.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> getAllActors();
    List<Actor> getActorsByFirstName(String firstName);
    List<Actor> getActorsByLastName(String lastName);
    Actor getActorById(Short id);
    Actor saveActor(Actor actor);
    Actor updateActor(Short id, Actor actor);
    Actor replaceActor(Short id, Actor actor);
    void deleteActor(Short id);
    void deleteActor(Actor actor);
}
