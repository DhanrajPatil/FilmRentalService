package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Short> {
}
