package com.elitefolk.filmrentalservice.exceptions;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StaffNotFoundException extends RuntimeException {
    private Byte staffId;
    public StaffNotFoundException(String msg, Byte id){
        super(msg);
        this.staffId = id;
    }
}
