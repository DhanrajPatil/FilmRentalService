package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.dtos.BasicStoreDto;
import com.elitefolk.filmrentalservice.exceptions.AddressNotFoundException;
import com.elitefolk.filmrentalservice.exceptions.StaffNotFoundException;
import com.elitefolk.filmrentalservice.models.Address;
import com.elitefolk.filmrentalservice.models.Staff;
import com.elitefolk.filmrentalservice.models.Store;
import com.elitefolk.filmrentalservice.repositories.AddressRepository;
import com.elitefolk.filmrentalservice.repositories.StaffRepository;
import com.elitefolk.filmrentalservice.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final StaffRepository staffRepository;

    public StoreServiceImpl(StoreRepository storeRepo,
                            AddressRepository addressRepo,
                            StaffRepository staffRepo) {
        this.storeRepository = storeRepo;
        this.addressRepository = addressRepo;
        this.staffRepository = staffRepo;
    }

    @Override
    public BasicStoreDto getStoreByManagerId(Byte managerId) {
        Store str = this.storeRepository.findByManagerId(managerId).orElse(null);
        if (str == null) {
            return null;
        } else {
            return new BasicStoreDto(str);
        }
    }

    @Override
    public BasicStoreDto getStoreById(Byte storeId) {
        Store str = this.storeRepository.findById(storeId).orElse(null);
        if (str == null) { return null; }
        return new BasicStoreDto(str);
    }

    @Override
    public BasicStoreDto saveStore(BasicStoreDto storeDto) {
        Store str = storeDto.toStore();
        Short addressId = storeDto.getAddressId();
        Address address = this.addressRepository.findById(addressId)
                .orElseThrow( () -> new AddressNotFoundException(
                        "Address with Id " + addressId + " not Exists",
                        str.getAddress()
                ));
        Byte managerId = storeDto.getManagerId();
        Staff manager = this.staffRepository.findById(managerId)
                .orElseThrow( () -> new StaffNotFoundException(
                        "Staff not exists with Id " + managerId,
                        storeDto.getManagerId()
                ));
        str.setAddress(address);
        str.setManager(manager);
        return new BasicStoreDto(this.storeRepository.save(str));
    }

    @Override
    public List<BasicStoreDto> getAllStores() {
        List<Store> stores = this.storeRepository.findAll();
        return BasicStoreDto.fromStoreList(stores);
    }
}
