package com.elitefolk.filmrentalservice.exceptions;

import com.elitefolk.filmrentalservice.dtos.BasicFilmDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmNotFoundException extends RuntimeException {
    private BasicFilmDto errorRelatedData;

    public FilmNotFoundException(String message, BasicFilmDto errorRelatedData) {
        super(message);
        this.errorRelatedData = errorRelatedData;
    }
}
