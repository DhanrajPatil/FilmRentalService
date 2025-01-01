package com.elitefolk.filmrentalservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActorNotFoundException extends RuntimeException {
    private Short errorRelatedData;
    public ActorNotFoundException(Short id) {
        super("Could not find actor");
        this.errorRelatedData = id;
    }
}
