package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Byte> {
}
