package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Customer;
import com.elitefolk.filmrentalservice.models.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    private Integer id;
    private String filmTitle;
    private Short filmYear;
    private Customer customer;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private Staff staff;

}
