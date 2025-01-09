package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Address;
import com.elitefolk.filmrentalservice.models.Staff;
import com.elitefolk.filmrentalservice.models.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicStoreDto {
    private Byte id;
    private Byte managerId;
    private String managerName;
    private Short addressId;
    private String address;
    private String city;
    private String postalCode;
    private Double penaltyPerDay;

    public BasicStoreDto(Store store) {
        this.id = store.getId();
        Staff manager = store.getManager();
        this.managerId = manager.getId();
        this.managerName = manager.getFirstName() + " " + manager.getLastName();
        Address address = store.getAddress();
        this.addressId = address.getId();
        this.address = address.getAddress();
        this.city = address.getCity().getName();
        this.postalCode = address.getPostalCode();
        this.penaltyPerDay = store.getPenaltyPerDay();
    }

    public Store toStore() {
        Store store = new Store();
        store.setId(this.id);
        Address address = new Address();
        address.setId(this.addressId);
        store.setAddress(address);
        Staff staff = new Staff();
        staff.setId(this.managerId);
        store.setManager(staff);
        store.setPenaltyPerDay(this.penaltyPerDay);
        return store;
    }

    public static BasicStoreDto fromStore(Store store) {
        return new BasicStoreDto(store);
    }

    public static List<BasicStoreDto> fromStoreList(List<Store> stores) {
        return stores.stream().map(BasicStoreDto::new).toList();
    }
}
