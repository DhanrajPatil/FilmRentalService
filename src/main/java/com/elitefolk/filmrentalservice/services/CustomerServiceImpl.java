package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.dtos.BasicCustomerDto;
import com.elitefolk.filmrentalservice.exceptions.AddressNotFoundException;
import com.elitefolk.filmrentalservice.exceptions.StoreNotFoundException;
import com.elitefolk.filmrentalservice.models.Address;
import com.elitefolk.filmrentalservice.models.Customer;
import com.elitefolk.filmrentalservice.models.Store;
import com.elitefolk.filmrentalservice.repositories.AddressRepository;
import com.elitefolk.filmrentalservice.repositories.CustomerRepository;
import com.elitefolk.filmrentalservice.repositories.StoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final StoreRepository storeRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               AddressRepository addressRepository,
                               StoreRepository storeRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public BasicCustomerDto addCustomer(BasicCustomerDto basicCustomerDto) {
        Short addId = basicCustomerDto.getAddressId();
        Address address = addressRepository.findById(addId)
                .orElseThrow(() -> new AddressNotFoundException(
                        "Address ot found with " + addId ,
                        basicCustomerDto.getAddress())
                );
        Customer cust = BasicCustomerDto.toCustomer(basicCustomerDto);
        cust.setAddress(address);
        Store str = storeRepository.findById(basicCustomerDto.getStoreId())
                .orElseThrow(() -> new StoreNotFoundException("Store Not found", basicCustomerDto.getStoreId()));
        cust.setStore(str);
        return new BasicCustomerDto(customerRepository.save(cust));
    }

    @Override
    public Page<BasicCustomerDto> getAllCustomers(Pageable pagable) {
        return this.customerRepository.findAll(pagable).map(BasicCustomerDto::new);
    }

    @Override
    public BasicCustomerDto getCustomerById(Short id){
        return this.customerRepository.findById(id).map(BasicCustomerDto::new).orElse(null);
    }

    @Override
    public List<BasicCustomerDto> getCustomerByFirstNameAndLastName(String firstName, String lastName) {
        return BasicCustomerDto.fromCustomerList(customerRepository.findByFirstNameAndLastName(firstName, lastName));
    }

    @Override
    public Page<BasicCustomerDto> getCustomerByNameQuery(String name, Pageable pageable) {
        if(name == null || name.isEmpty()) {
            return this.customerRepository.findAll(pageable).map(BasicCustomerDto::new);
        }
        return customerRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                name,
                name,
                pageable
        ).map(BasicCustomerDto::new);
    }
}
