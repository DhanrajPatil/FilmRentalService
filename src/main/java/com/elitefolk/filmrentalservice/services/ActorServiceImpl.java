package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.exceptions.ActorNotFoundException;
import com.elitefolk.filmrentalservice.models.Actor;
import com.elitefolk.filmrentalservice.repositories.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> getAllActors() {
        return this.actorRepository.findAll();
    }

    @Override
    public List<Actor> getActorsByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Actor> getActorsByLastName(String lastName) {
        return null;
    }

    @Override
    public Actor getActorById(Short id) {
        return this.actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    @Override
    public Actor saveActor(Actor actor) {
        return this.actorRepository.save(actor);
    }

    @Override
    public Actor patchActor(Short id, Actor actor) {
        Actor act = this.actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(actor.getId()));
        if(actor.getFirstName() != null) {
            act.setFirstName(actor.getFirstName());
        }
        if(actor.getLastName() != null) {
            act.setLastName(actor.getLastName());
        }
        return this.actorRepository.save(act);
    }

    @Override
    public Actor replaceActor(Short id, Actor actor) {
        return this.actorRepository.save(actor);
    }

    @Override
    public void deleteActor(Short id) {

    }

    @Override
    public void deleteActor(Actor actor) {

    }

    @Override
    public List<Actor> fetchOrCreateActors(List<Actor> actors) {
        List<Actor> persistedActors = new ArrayList<>();
        for (Actor actor : actors) {
            Actor persistedActor;
            if(actor.getId() == null) {
                persistedActor = actorRepository.findByFirstNameAndLastName(actor.getFirstName(), actor.getLastName())
                        .orElseGet(() -> actorRepository.save(actor) );
            } else {
                persistedActor = actorRepository.findById(actor.getId())
                        .orElseGet(() -> {
                            actor.setId(null);
                            return actorRepository.save(actor);
                        }); // Save new actor if not present
            }
            persistedActors.add(persistedActor);
        }
        return persistedActors;
    }
}
