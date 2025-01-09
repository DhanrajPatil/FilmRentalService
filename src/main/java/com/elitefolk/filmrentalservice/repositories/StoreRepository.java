package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Byte> {
    Optional<Store> findByManagerId(Byte managerId);
}
