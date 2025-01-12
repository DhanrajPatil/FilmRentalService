package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.dtos.InventoryDto;
import com.elitefolk.filmrentalservice.dtos.RentalDto;
import com.elitefolk.filmrentalservice.exceptions.*;
import com.elitefolk.filmrentalservice.models.*;
import com.elitefolk.filmrentalservice.repositories.CustomerRepository;
import com.elitefolk.filmrentalservice.repositories.InventoryRepository;
import com.elitefolk.filmrentalservice.repositories.RentalRepository;
import com.elitefolk.filmrentalservice.repositories.StaffRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    public RentalServiceImpl(RentalRepository rentalRepository,
                             InventoryRepository inventoryRepository,
                             CustomerRepository customerRepository,
                             StaffRepository staffRepository) {
        this.rentalRepository = rentalRepository;
        this.inventoryRepository = inventoryRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }


    @Override
    public RentalDto getRentalById(Integer id) {
        Rental rental = this.rentalRepository.findById(id).orElse(null);
        return rental == null ? null : new RentalDto(rental);
    }

    @Override
    public Page<RentalDto> getAllRentals(Pageable pageable) {
        Page<Rental> rentals = this.rentalRepository.findAll(pageable);
        return rentals.map(RentalDto::new);
    }

    @Override
    @Transactional
    public RentalDto addRental(RentalDto rentalDto) {
        Rental rental = rentalDto.toRental();

        //Agreed Return Date can't be null
        if(rental.getAgreedReturnDate() == null) {
            throw new AgreedReturnDateNotProvidedException("Agreed Return Date should not be null", rentalDto);
        }

        // Set Rental date to current Date if not present
        if(rental.getRentalDate() == null) {
            rental.setRentalDate(LocalDate.now());
        }
        Inventory inventory = this.inventoryRepository.findById(rentalDto.getInventoryId())
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with Id" + rentalDto.getInventoryId(), null));

        // Check if Inventory is available.
        if(inventory.getRentalStatus() != RentalStatus.NOT_RENTED) {
            throw new InventoryNotAvailableException("This inventory Not Available - Already rented Out", new InventoryDto(inventory));
        }
        inventory.setRentalStatus(RentalStatus.IN_PROCESS);
        this.inventoryRepository.save(inventory);
        rental.setInventory(inventory);

        // Find the staff from DB and set it.
        Staff staff = this.staffRepository.findById(rentalDto.getStaffId())
                .orElseThrow(() -> new StaffNotFoundException("Staff Not found with Id" + rentalDto.getStaffId(), null));
        rental.setStaff(staff);

        // Find the staff from DB and set it.
        Customer customer = this.customerRepository.findById(rentalDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not found with Id" + rentalDto.getCustomerId(), null));
        rental.setCustomer(customer);

        rental = this.rentalRepository.save(rental);
        return new RentalDto(rental);
    }

    @Override
    @Transactional
    public RentalDto updateRental(Integer id, RentalDto rentalDto) {
        Rental existingRental = this.rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException("Rental not found with Id" + id, rentalDto));
        Rental newRental = rentalDto.toRental();
        if(newRental.getReturnDate() != null && existingRental.getReturnDate() == null) {
            existingRental.setReturnDate(newRental.getReturnDate());
            Inventory inventory = this.inventoryRepository.findById(existingRental.getInventory().getId())
                    .orElse(null);
            if(inventory != null) {
                inventory.setRentalStatus(RentalStatus.NOT_RENTED);
                this.inventoryRepository.save(inventory);
            }
        }
        existingRental = this.rentalRepository.save(existingRental);
        return new RentalDto(existingRental);
    }

    @Override
    @Transactional
    public List<RentalDto> getAllRentalsByFilm(Short filmId) {
        return this.rentalRepository.getRentalsByFilmId(filmId);
    }

    @Override
    public Page<RentalDto> getAllRentalsByStore(Byte storeId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RentalDto> getAllRentalsByStaff(Byte staffId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RentalDto> getAllRentalWhichAreReturned(Pageable pageable) {
        return null;
    }

    @Override
    public Page<RentalDto> getAllRentalWhichAreNotReturned(Pageable pageable) {
        return null;
    }
}
