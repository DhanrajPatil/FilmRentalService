package com.elitefolk.filmrentalservice.exceptions;

import com.elitefolk.filmrentalservice.dtos.InventoryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryNotFoundException extends RuntimeException{
    private InventoryDto dto;
    public InventoryNotFoundException(String msg, InventoryDto dto) {
        super(msg);
        this.dto = dto;
    }
}
