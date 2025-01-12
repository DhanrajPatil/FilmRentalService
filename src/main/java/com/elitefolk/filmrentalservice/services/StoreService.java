package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.dtos.BasicStoreDto;
import com.elitefolk.filmrentalservice.models.Store;

import java.util.List;

public interface StoreService {
    BasicStoreDto getStoreByManagerId(Byte managerId);
    BasicStoreDto getStoreDtoById(Byte storeId);
    Store getStoreById(Byte storeId);
    BasicStoreDto saveStore (BasicStoreDto storeDto);
    List<BasicStoreDto> getAllStores();
}
