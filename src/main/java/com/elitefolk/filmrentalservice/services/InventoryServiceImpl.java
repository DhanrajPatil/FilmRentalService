package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.dtos.FilmAvailabilityDto;
import com.elitefolk.filmrentalservice.dtos.InventoryDto;
import com.elitefolk.filmrentalservice.exceptions.FilmNotFoundException;
import com.elitefolk.filmrentalservice.exceptions.InventoryNotFoundException;
import com.elitefolk.filmrentalservice.exceptions.StoreNotFoundException;
import com.elitefolk.filmrentalservice.models.Film;
import com.elitefolk.filmrentalservice.models.Inventory;
import com.elitefolk.filmrentalservice.models.RentalStatus;
import com.elitefolk.filmrentalservice.models.Store;
import com.elitefolk.filmrentalservice.repositories.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;
    private final FilmService filmService;
    private final StoreService storeService;

    public InventoryServiceImpl(InventoryRepository inventoryRepository,
                                FilmService filmService,
                                StoreService storeService) {
        this.inventoryRepository = inventoryRepository;
        this.filmService = filmService;
        this.storeService = storeService;
    }

    @Override
    public Page<InventoryDto> getInventoryByRentalStatus(RentalStatus rentalStatus, Pageable pageable) {
        Page<Inventory> inventories = this.inventoryRepository.findByRentalStatus(rentalStatus, pageable);
        return inventories.map(InventoryDto::new);
    }

    @Override
    public InventoryDto getInventoryDtoById(Integer id) {
        Inventory inventory = getInventoryById(id);
        return inventory == null ? null : new InventoryDto(inventory);
    }

    @Override
    public Inventory getInventoryById(Integer id) {
        return this.inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with id" + id, null));
    }

    @Override
    @Transactional
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        Inventory inventory = this.getInventoryFromDto(inventoryDto);
        inventory = this.inventoryRepository.save(inventory);
        return new InventoryDto(inventory);
    }

    public Inventory getInventoryFromDto(InventoryDto inventoryDto) {
        Inventory inventory = inventoryDto.toInventory();
        return getInventoryWithPersistedRecords(inventory);
    }

    public Inventory getInventoryWithPersistedRecords(Inventory inventory) {
        Film film = this.filmService.getFilmById(inventory.getFilm().getId());
        if(film == null) {
            throw new FilmNotFoundException("Film not Found with " + inventory.getFilm().getId(), null);
        }
        Store store = this.storeService.getStoreById(inventory.getStore().getId());
        if(store == null) {
            throw new StoreNotFoundException("Store not Found", inventory.getStore().getId());
        }
        inventory.setFilm(film);
        inventory.setStore(store);
        return inventory;
    }

    @Override
    public InventoryDto replaceInventory(Integer id, InventoryDto inventoryDto) {
        Inventory inventory = this.inventoryRepository.findById(id)
                .orElseThrow( () ->
                    new InventoryNotFoundException("Inventory not found with " + id, inventoryDto)
                );
        Inventory upcomingInventory = this.getInventoryFromDto(inventoryDto);
        upcomingInventory.setId(id);
        inventory = this.inventoryRepository.save(inventory);
        return new InventoryDto(inventory);
    }

    @Override
    public InventoryDto patchInventory(Integer id, InventoryDto inventoryDto) {
        Inventory inventory = this.inventoryRepository.findById(id)
                .orElseThrow( () ->
                        new InventoryNotFoundException("Inventory not found with " + id, inventoryDto)
                );
        Inventory upcomingInventory = inventoryDto.toInventory();
        upcomingInventory.setId(id);
        if(upcomingInventory.getFilm() != null) {
            inventory.setFilm(upcomingInventory.getFilm());
        }
        if(upcomingInventory.getStore() != null) {
            inventory.setStore(upcomingInventory.getStore());
        }
        if(upcomingInventory.getRentalStatus() != null) {
            inventory.setRentalStatus(upcomingInventory.getRentalStatus());
        }
        inventory = getInventoryWithPersistedRecords(inventory);
        inventory = this.inventoryRepository.save(inventory);
        return new InventoryDto(inventory);
    }

    @Override
    @Transactional
    public List<FilmAvailabilityDto> getInventoryByFilmIdAndStoreId(Short filmId, Byte storeId, Short sizeOfPage, Short pageNo) {
        return this.inventoryRepository.getFilmsWithInventoryAvailableCount(filmId, storeId, sizeOfPage, pageNo);
    }
}
