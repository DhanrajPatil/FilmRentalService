package com.elitefolk.filmrentalservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {
    private Integer id;
    private Short filmId;
    private String title;
    private Integer releaseYear;
    private Double rentalRate;



}
