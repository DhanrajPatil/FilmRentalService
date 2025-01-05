package com.elitefolk.filmrentalservice.controllers;


import com.elitefolk.filmrentalservice.dtos.BasicCustomerDto;
import com.elitefolk.filmrentalservice.dtos.PaginatedResponse;
import com.elitefolk.filmrentalservice.services.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public PaginatedResponse<BasicCustomerDto> getAllCustomer(Pageable pagable) {
        return new PaginatedResponse<>(customerService.getAllCustomers(pagable));
    }

    @GetMapping("/query/")
    public PaginatedResponse<BasicCustomerDto> getAllCustomerByName(@RequestParam String name, @RequestParam Pageable pagable) {
        return new PaginatedResponse<>(customerService.getCustomerByNameQuery(name, pagable));
    }

    @GetMapping("/{id}")
    public BasicCustomerDto getCustomer(@PathVariable Short id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping()
    public BasicCustomerDto createCustomer(@RequestBody BasicCustomerDto basicCustomerDto) {
        return customerService.addCustomer(basicCustomerDto);
    }
}
