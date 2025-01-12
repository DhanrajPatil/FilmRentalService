package com.elitefolk.filmrentalservice.controllers;

import com.elitefolk.filmrentalservice.dtos.RentalDto;
import com.elitefolk.filmrentalservice.models.Rental;
import com.elitefolk.filmrentalservice.services.RentalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public Page<RentalDto> getAllRentals(Pageable pageable) {
        return this.rentalService.getAllRentals(pageable);
    }

    @GetMapping("/{id}")
    public RentalDto getRentalById(@PathVariable Integer id) {
        return this.rentalService.getRentalById(id);
    }

    @PostMapping
    public RentalDto createRental(@RequestBody RentalDto rentalDto) {
        return this.rentalService.addRental(rentalDto);
    }

    @PutMapping("/{id}")
    public RentalDto updateRental(@PathVariable Integer id, @RequestBody RentalDto rentalDto) {
        return this.rentalService.updateRental(id, rentalDto);
    }

    @GetMapping("/byFilm/{filmId}")
    public List<RentalDto> getRentalsByFilm(@PathVariable Short filmId) {
        return this.rentalService.getAllRentalsByFilm(filmId);
    }
}
