package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.dtos.RentalDto;
import com.elitefolk.filmrentalservice.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    @Procedure(name = "Rental.getAllRentalsByFilm")
    List<RentalDto> getRentalsByFilmId(@Param("filmId") Short filmId);
}
