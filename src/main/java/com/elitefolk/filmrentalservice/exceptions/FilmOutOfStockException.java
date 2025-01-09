package com.elitefolk.filmrentalservice.exceptions;

import com.elitefolk.filmrentalservice.models.Film;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmOutOfStockException extends RuntimeException{
    private Film film;
    public FilmOutOfStockException(String msg, Film film) {
        super(msg);
        this.film = film;
    }
}
