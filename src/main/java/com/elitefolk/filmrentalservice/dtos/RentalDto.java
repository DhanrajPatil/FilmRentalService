package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    private Integer id;

    //Inventory Details
    private Integer inventoryId;

    //Film Details
    private Short filmId;
    private String filmTitle;
    private Integer releaseYear;
    private Double rentalRate;

    //Customer Details
    private Short customerId;
    private String customerName;
    private String customerEmail;

    //Staff Details
    private Byte staffId;
    private String staffName;
    private String staffEmail;

    //Rental Details
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private LocalDate agreedReturnDate;

    public RentalDto(Rental rental) {
        this.id = rental.getId();

        Inventory inventory = rental.getInventory();
        this.inventoryId = inventory.getId();

        Film film = inventory.getFilm();
        this.filmId = film.getId();
        this.filmTitle = film.getTitle();
        this.releaseYear = film.getReleaseYear();
        this.rentalRate = film.getRentalRate();

        Customer cust = rental.getCustomer();
        this.customerId = cust.getId();
        this.customerName = cust.getFirstName() + " "  + cust.getLastName();
        this.customerEmail = cust.getEmail();

        Staff stf = rental.getStaff();
        this.staffId = stf.getId();
        this.staffEmail = stf.getEmail();
        this.staffName = stf.getFirstName() + " " + stf.getLastName();

        this.rentalDate = rental.getRentalDate();
        this.returnDate = rental.getReturnDate();
        this.agreedReturnDate = rental.getAgreedReturnDate();
    }

    public static RentalDto fromRental(Rental rental) {
        return new RentalDto(rental);
    }

    public static List<RentalDto> fromRentalList(List<Rental> rentals) {
        return rentals.stream().map(RentalDto::new).toList();
    }

    public Rental toRental() {
        Rental rental = new Rental();
        rental.setId(this.id);
        rental.setRentalDate(this.rentalDate);
        rental.setReturnDate(this.returnDate);
        rental.setAgreedReturnDate(this.agreedReturnDate);

        Inventory inventory = new Inventory();
        inventory.setId(this.inventoryId);
        rental.setInventory(inventory);

        Staff staff = new Staff();
        staff.setId(this.staffId);
        rental.setStaff(staff);

        Customer cust = new Customer();
        cust.setId(this.customerId);
        rental.setCustomer(cust);
        return rental;
    }
}
