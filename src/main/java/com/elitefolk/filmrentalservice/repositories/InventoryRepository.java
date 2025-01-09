package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findByFilmId(Short filmId);
    List<Inventory> findByStoreId(Byte storeId);
    List<Inventory> findByFilmIdAndStoreId(Short filmId, Byte storeId);
}
