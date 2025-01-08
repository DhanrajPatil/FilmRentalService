package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.models.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    Optional<Rental> getRentalById(long id);
    Page<Rental> getAllRentals(Pageable pageable);
    Rental addRental(Rental rental);
    Rental updateRental(Rental rental);
}
