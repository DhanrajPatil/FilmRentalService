package com.elitefolk.filmrentalservice.controllers;

import com.elitefolk.filmrentalservice.dtos.BasicStoreDto;
import com.elitefolk.filmrentalservice.services.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }
    @GetMapping
    public List<BasicStoreDto> getAllStores() {
        return this.storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public BasicStoreDto getStoreBYId(@PathVariable Byte id) {
        return this.storeService.getStoreById(id);
    }

    @PostMapping()
    public BasicStoreDto saveStore(@RequestBody BasicStoreDto dto) {
        return this.storeService.saveStore(dto);
    }

    @GetMapping("/manager/{managerId}")
    public BasicStoreDto getStoreByManager(@PathVariable Byte managerId) {
        return storeService.getStoreByManagerId(managerId);
    }
}
