package com.elitefolk.filmrentalservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerNotFoundException extends RuntimeException {
    private String emailId;
    public CustomerNotFoundException(String msg, String emailId) {
        super(msg);
        this.emailId = emailId;
    }
}
