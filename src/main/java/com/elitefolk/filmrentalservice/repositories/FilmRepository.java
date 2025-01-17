package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Short> {
    Page<Film> findAll(Pageable pageable);

    List<Film> findByRating(String rating);

    Optional<Film> findById(Short id);

    List<Film> findByLanguageName(String name);

    List<Film> findByTitle(String title);

    List<Film> findByReleaseYear(Integer year);
}
