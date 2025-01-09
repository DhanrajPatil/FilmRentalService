package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.dtos.BasicStoreDto;

import java.util.List;

public interface StoreService {
    BasicStoreDto getStoreByManagerId(Byte managerId);
    BasicStoreDto getStoreById(Byte storeId);
    BasicStoreDto saveStore (BasicStoreDto storeDto);
    List<BasicStoreDto> getAllStores();
}
