package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Short> {
    List<Actor> findAll();
    List<Actor> findByFirstNameContains(String firstName);
    List<Actor> findByLastNameContains(String lastName);
    Optional<Actor> findById(Short id);
    <S extends Actor> S save(S entity);
    void deleteById(Short id);
    void delete(Actor entity);
}
