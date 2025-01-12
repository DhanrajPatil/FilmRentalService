package com.elitefolk.filmrentalservice.exceptions;

import com.elitefolk.filmrentalservice.dtos.RentalDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalNotFoundException extends RuntimeException{
    private RentalDto rentalDto;
    public RentalNotFoundException(String msg, RentalDto rentalDto) {
        super(msg);
        this.rentalDto = rentalDto;
    }
}
