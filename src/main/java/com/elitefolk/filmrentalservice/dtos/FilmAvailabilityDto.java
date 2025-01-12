package com.elitefolk.filmrentalservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmAvailabilityDto {
    private Short filmId;
    private String title;
    private Integer releaseYear;
    private Double rentalRate;
    private Byte storeId;
    private Short availableCopies;
}
