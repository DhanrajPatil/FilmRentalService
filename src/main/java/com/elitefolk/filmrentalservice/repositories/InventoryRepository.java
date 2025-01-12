package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.dtos.FilmAvailabilityDto;
import com.elitefolk.filmrentalservice.models.Inventory;
import com.elitefolk.filmrentalservice.models.RentalStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Page<Inventory> findByRentalStatus(RentalStatus rentalStatus, Pageable pagable);

    @Procedure(name = "Inventory.getFilmsWithInventoryAvailableCount")
    List<FilmAvailabilityDto> getFilmsWithInventoryAvailableCount(@Param("filmId") Short filmId,
                                                                  @Param("storeId") Byte storeId,
                                                                  @Param("sizeOfPage") Short sizeOfPage,
                                                                  @Param("pageNo") Short pageNo);
}
