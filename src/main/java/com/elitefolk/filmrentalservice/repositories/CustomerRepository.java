package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Short> {
    Optional<Customer> findByEmail(String email);

    Page<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName,
            Pageable pageable
    );


    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}