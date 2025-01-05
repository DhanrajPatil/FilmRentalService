package com.elitefolk.filmrentalservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreNotFoundException extends RuntimeException {
    Byte storeId;
    public StoreNotFoundException(String message, Byte storeId) {
        super(message);
        this.storeId = storeId;
    }
}
