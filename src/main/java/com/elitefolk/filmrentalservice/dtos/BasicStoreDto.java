package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Address;
import com.elitefolk.filmrentalservice.models.Staff;
import com.elitefolk.filmrentalservice.models.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    private Double chargesPerDay;
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
        this.chargesPerDay = store.getChargesPerDay();
        this.penaltyPerDay = store.getPenaltyPerDay();
    }

    public Store toModel() {
        Store store = new Store();
        store.setId(this.id);
        Address address = new Address();
        address.setId(this.addressId);
        store.setAddress(address);
        Staff staff = new Staff();
        staff.setId(this.managerId);
        store.setManager(staff);
        store.setChargesPerDay(this.chargesPerDay) ;
        store.setPenaltyPerDay(this.penaltyPerDay);
        return store;
    }

    public static BasicStoreDto toDto(Store store) {
        BasicStoreDto dto = new BasicStoreDto(store);
        return dto;
    }

    public static List<BasicStoreDto> toDtos(List<Store> stores) {
        List<BasicStoreDto> dtos = new ArrayList<>();
        for (Store store : stores) {
            dtos.add(toDto(store));
        }
        return dtos;
    }
}
