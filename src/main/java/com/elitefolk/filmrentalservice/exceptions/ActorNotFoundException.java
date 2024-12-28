package com.elitefolk.filmrentalservice.exceptions;

public class ActorNotFoundException extends RuntimeException {
    public ActorNotFoundException(Short id) {
        super("Could not find actor " + id);
    }
}
