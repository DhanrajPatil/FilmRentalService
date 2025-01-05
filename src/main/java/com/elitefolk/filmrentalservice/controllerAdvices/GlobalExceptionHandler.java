package com.elitefolk.filmrentalservice.controllerAdvices;

import com.elitefolk.filmrentalservice.dtos.BasicFilmDto;
import com.elitefolk.filmrentalservice.dtos.GlobalErrorDto;
import com.elitefolk.filmrentalservice.exceptions.ActorNotFoundException;
import com.elitefolk.filmrentalservice.exceptions.AddressNotFoundException;
import com.elitefolk.filmrentalservice.exceptions.FilmNotFoundException;
import com.elitefolk.filmrentalservice.exceptions.StoreNotFoundException;
import com.elitefolk.filmrentalservice.models.Store;
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
    public ResponseEntity<GlobalErrorDto<String>> handleAddressNotFoundException(AddressNotFoundException e) {
        GlobalErrorDto<String> errorDto = new GlobalErrorDto<>(e.getMessage(), "NOT FOUND", e.getAddress());
        return ResponseEntity.status(404).body(errorDto);
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<GlobalErrorDto<Byte>> handleStoreNotFoundException(StoreNotFoundException e) {
        GlobalErrorDto<Byte> errorDto = new GlobalErrorDto<>(e.getMessage(), "NOT FOUND", e.getStoreId());
        return ResponseEntity.status(404).body(errorDto);
    }

}
