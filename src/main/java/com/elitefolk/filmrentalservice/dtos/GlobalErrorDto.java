package com.elitefolk.filmrentalservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalErrorDto<T> {
    private String message;
    private String status;
    private T errorRelatedData;
}
