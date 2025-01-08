package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.dtos.BasicCustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    BasicCustomerDto addCustomer(BasicCustomerDto customerDto);
    Page<BasicCustomerDto> getAllCustomers(Pageable pagable);
    BasicCustomerDto getCustomerById(Short id);
    List<BasicCustomerDto> getCustomerByFirstNameAndLastName(String firstName, String lastName);
    Page<BasicCustomerDto> getCustomerByNameQuery(String firstName, Pageable pageable);
}
