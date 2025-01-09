package com.elitefolk.filmrentalservice.exceptions;

import com.elitefolk.filmrentalservice.models.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressNotFoundException extends RuntimeException {
    private Address address;

    public AddressNotFoundException(String message, Address address) {
        super(message);
        this.address = address;
    }
}
