package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.dtos.RentalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    RentalDto getRentalById(Integer id);
    Page<RentalDto> getAllRentals(Pageable pageable);
    RentalDto addRental(RentalDto rental);
    RentalDto updateRental(Integer id, RentalDto rental);
    List<RentalDto> getAllRentalsByFilm(Short filmId);
    Page<RentalDto> getAllRentalsByStore(Byte storeId, Pageable pageable);
    Page<RentalDto> getAllRentalsByStaff(Byte staffId, Pageable pageable);
    Page<RentalDto> getAllRentalWhichAreReturned(Pageable pageable);
    Page<RentalDto> getAllRentalWhichAreNotReturned(Pageable pageable);
}
