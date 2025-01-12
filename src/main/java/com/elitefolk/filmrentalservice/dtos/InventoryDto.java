package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {
    private Integer id;
    private Short filmId;
    private String filmTitle;
    private Integer filmReleaseYear;
    private Double filmRentalRate;
    private Byte storeId;
    private String storeAddress;
    private String storeCity;
    private String storePostalCode;
    private RentalStatus rentalStatus;

    public InventoryDto(Inventory inventory) {
        this.id = inventory.getId();
        Film film = inventory.getFilm();
        this.filmId = film.getId();
        this.filmTitle = film.getTitle();
        this.filmReleaseYear = film.getReleaseYear();
        this.filmRentalRate = film.getRentalRate();
        Store store = inventory.getStore();
        this.storeId = store.getId();
        this.storeAddress = store.getAddress().getAddress();
        this.storeCity = store.getAddress().getCity().getName();
        this.storePostalCode = store.getAddress().getPostalCode();
        this.rentalStatus = inventory.getRentalStatus();
    }

    public static InventoryDto fromInventory(Inventory inventory) {
        return new InventoryDto(inventory);
    }

    public Inventory toInventory() {
        Film film = new Film();
        film.setId(this.filmId);
        film.setTitle(this.filmTitle);
        film.setReleaseYear(this.filmReleaseYear);
        film.setRentalRate(this.filmRentalRate);
        Store store = new Store();
        store.setId(this.storeId);
        Inventory inventory = new Inventory();
        inventory.setId(this.id);
        inventory.setFilm(film);
        inventory.setStore(store);
        inventory.setRentalStatus(this.rentalStatus);
        return inventory;
    }

    public static List<InventoryDto> fromInventories(List<Inventory> inventories) {
        return inventories.stream().map(InventoryDto::new).toList();
    }
}
