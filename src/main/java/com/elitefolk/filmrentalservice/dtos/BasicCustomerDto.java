package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Address;
import com.elitefolk.filmrentalservice.models.City;
import com.elitefolk.filmrentalservice.models.Customer;
import com.elitefolk.filmrentalservice.models.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicCustomerDto {
    private Short id;
    private String firstName;
    private String lastName;
    private String email;
    private Short addressId;
    private String address;
    private String address2;
    private String district;
    private Short cityId;
    private String city;
    private String state;
    private String postalCode;
    private Byte storeId;

    public BasicCustomerDto(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        Address add = customer.getAddress();
        if(add != null) {
            this.addressId = add.getId();
            this.address = add.getAddress();
            this.address2 = add.getAddress2();
            this.district = add.getDistrict();
            if (add.getCity() != null) {
                this.cityId = add.getCity().getId();
                this.city = add.getCity().getName();
            }
        }
        this.storeId = customer.getStore().getId();
    }

    public static BasicCustomerDto fromCustomer(Customer customer) {
        return new BasicCustomerDto(customer);
    }

    public static Customer toCustomer(BasicCustomerDto basicCustomerDto) {
        Customer customer = new Customer();
        customer.setId(basicCustomerDto.getId());
        customer.setFirstName(basicCustomerDto.getFirstName());
        customer.setLastName(basicCustomerDto.getLastName());
        customer.setEmail(basicCustomerDto.getEmail());
        return customer;
    }

    public static List<BasicCustomerDto> fromCustomerList(List<Customer> customers) {
        List<BasicCustomerDto> basicCustomerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            basicCustomerDtos.add(fromCustomer(customer));
        }
        return basicCustomerDtos;
    }

}
