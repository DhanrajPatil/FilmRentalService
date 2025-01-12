package com.elitefolk.filmrentalservice.exceptions;

import com.elitefolk.filmrentalservice.dtos.RentalDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgreedReturnDateNotProvidedException extends RuntimeException{
    RentalDto rentalDto;
    public AgreedReturnDateNotProvidedException(String msg, RentalDto rentalDto) {
        super(msg);
        this.rentalDto = rentalDto;
    }
}
