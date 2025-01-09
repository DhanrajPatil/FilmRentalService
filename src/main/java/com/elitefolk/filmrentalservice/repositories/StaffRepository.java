package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Byte> {
}
