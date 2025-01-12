package com.elitefolk.filmrentalservice.controllers;

import com.elitefolk.filmrentalservice.dtos.FilmAvailabilityDto;
import com.elitefolk.filmrentalservice.dtos.InventoryDto;
import com.elitefolk.filmrentalservice.services.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {
    private final InventoryService inventoryService;;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{id}")
    public InventoryDto getInventoryById(@PathVariable Integer id) {
        return this.inventoryService.getInventoryDtoById(id);
    }

    @PostMapping()
    public InventoryDto createInventory(@RequestBody InventoryDto inventoryDto) {
        return this.inventoryService.createInventory(inventoryDto);
    }

    @PutMapping("/{id}")
    public InventoryDto updateInventory(@PathVariable Integer id, @RequestBody InventoryDto inventoryDto) {
        return this.inventoryService.replaceInventory(id, inventoryDto);
    }

    @PatchMapping("/{id}")
    public InventoryDto patchInventory(@PathVariable Integer id, @RequestBody InventoryDto inventoryDto) {
        return this.inventoryService.patchInventory(id, inventoryDto);
    }

    @GetMapping("/availability")
    public List<FilmAvailabilityDto> getAvailableInventories(@RequestParam(required = false) Short filmId,
                                                             @RequestParam(required = false) Byte storeId,
                                                             @RequestParam(required = false) Short pageSize,
                                                             @RequestParam(required = false) Short pageNo) {
        return this.inventoryService.getInventoryByFilmIdAndStoreId(filmId, storeId, pageSize, pageNo);
    }
}
