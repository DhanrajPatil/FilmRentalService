package com.elitefolk.filmrentalservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressNotFoundException extends RuntimeException {
    private String address;

    public AddressNotFoundException(String message, String address) {
        super(message);
        this.address = address;
    }
}
