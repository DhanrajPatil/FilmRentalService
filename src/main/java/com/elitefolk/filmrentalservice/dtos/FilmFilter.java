package com.elitefolk.filmrentalservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmFilter {
    Integer fromReleaseYear;
    Integer toReleaseYear;
    String title;
    String language;
    String category;
    String rating;
    String actor;
    Double rentalRate;
    Integer length;
    Integer fromLength;
    Integer toLength;
    Double replacementCost;

}
