package com.elitefolk.filmrentalservice.controllerAdvices;

import com.elitefolk.filmrentalservice.dtos.BasicFilmDto;
import com.elitefolk.filmrentalservice.dtos.GlobalErrorDto;
import com.elitefolk.filmrentalservice.dtos.InventoryDto;
import com.elitefolk.filmrentalservice.dtos.RentalDto;
import com.elitefolk.filmrentalservice.exceptions.*;
import com.elitefolk.filmrentalservice.models.Address;
import com.elitefolk.filmrentalservice.models.Film;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FilmNotFoundException.class)
    public ResponseEntity<GlobalErrorDto<BasicFilmDto>> handleFilmNotFoundException(FilmNotFoundException e) {
        GlobalErrorDto<BasicFilmDto> errorDto = new GlobalErrorDto<>(e.getMessage(), "NOT FOUND", e.getErrorRelatedData());
        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(ActorNotFoundException.class)
    public ResponseEntity<GlobalErrorDto<String>> handleActorNotFoundException(ActorNotFoundException e) {
        GlobalErrorDto<String> errorDto = new GlobalErrorDto<>(e.getMessage(), "NOT FOUND", e.getErrorRelatedData().toString());
        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<GlobalErrorDto<Address>> handleAddressNotFoundException(AddressNotFoundException e) {
        GlobalErrorDto<Address> errorDto = new GlobalErrorDto<>(e.getMessage(), "NOT FOUND", e.getAddress());
        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<GlobalErrorDto<Byte>> handleStoreNotFoundException(StoreNotFoundException e) {
        GlobalErrorDto<Byte> errorDto = new GlobalErrorDto<>(e.getMessage(), "NOT FOUND", e.getStoreId());
        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(StaffNotFoundException.class)
    public ResponseEntity<GlobalErrorDto<Byte>> handleStaffNotFoundException(StaffNotFoundException e) {
        GlobalErrorDto<Byte> errorDto = new GlobalErrorDto<>(e.getMessage(), "NOT FOUND", e.getStaffId());
        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(FilmOutOfStockException.class)
    public ResponseEntity<GlobalErrorDto<Film>> handleFilmOutOfStockException(FilmOutOfStockException e) {
        GlobalErrorDto<Film> err = new GlobalErrorDto<>(e.getMessage(), "Out of Stock", e.getFilm());
        return ResponseEntity.status(400).body(err);
    }

    @ExceptionHandler(InventoryNotAvailableException.class)
    public ResponseEntity<GlobalErrorDto<InventoryDto>> handleInventoryNotAvailableException(InventoryNotAvailableException e) {
        GlobalErrorDto<InventoryDto> errDto = new GlobalErrorDto<>(e.getMessage(), "RENTED ALREADY", e.getInventoryDto());
        return ResponseEntity.status(400).body(errDto);
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<GlobalErrorDto<InventoryDto>> handleInventoryNotFoundException(InventoryNotFoundException e) {
        GlobalErrorDto<InventoryDto> errorDto = new GlobalErrorDto<>(e.getMessage(), "NOT FOUND", e.getDto());
        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(AgreedReturnDateNotProvidedException.class)
    public ResponseEntity<GlobalErrorDto<RentalDto>> handleAgreedReturnDateNotProvided(AgreedReturnDateNotProvidedException e) {
        GlobalErrorDto<RentalDto> dto = new GlobalErrorDto<>(e.getMessage(), "Bad Parameter", e.getRentalDto());
        return ResponseEntity.status(400).body(dto);
    }
}
