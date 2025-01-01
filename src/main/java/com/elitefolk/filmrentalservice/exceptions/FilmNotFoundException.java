package com.elitefolk.filmrentalservice.exceptions;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(String message) {
        super(message);
    }
}
