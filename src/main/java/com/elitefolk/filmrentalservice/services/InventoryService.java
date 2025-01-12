package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.dtos.FilmAvailabilityDto;
import com.elitefolk.filmrentalservice.dtos.InventoryDto;
import com.elitefolk.filmrentalservice.models.Inventory;
import com.elitefolk.filmrentalservice.models.RentalStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InventoryService {
    Page<InventoryDto> getInventoryByRentalStatus(RentalStatus rentalStatus, Pageable pageable);
    InventoryDto getInventoryDtoById(Integer id);
    Inventory getInventoryById(Integer id);
    InventoryDto createInventory(InventoryDto inventory);
    InventoryDto replaceInventory(Integer id, InventoryDto inventory);
    InventoryDto patchInventory(Integer id, InventoryDto inventory);
    List<FilmAvailabilityDto> getInventoryByFilmIdAndStoreId(Short filmId, Byte storeId, Short sizeOfPage, Short pageNo);
}
