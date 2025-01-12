package com.elitefolk.filmrentalservice.exceptions;

import com.elitefolk.filmrentalservice.dtos.InventoryDto;
import com.elitefolk.filmrentalservice.models.Inventory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryNotAvailableException extends RuntimeException {
    private InventoryDto inventoryDto;
    public InventoryNotAvailableException(String msg, InventoryDto dto) {
        super(msg);
        this.inventoryDto = dto;
    }
}
