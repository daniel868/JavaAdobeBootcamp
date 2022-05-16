package com.example.adobe.controller;

import com.example.adobe.dto.CustomerDto;
import com.example.adobe.dto.CustomerFlightDto;
import com.example.adobe.entity.people.Customer;
import com.example.adobe.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/insert-customer")
    public ResponseEntity<Customer> insertNewCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.insertCustomer(customerDto);
    }

    @PostMapping("/book-customer")
    public ResponseEntity<CustomerFlightDto> bookFlight(@RequestBody CustomerFlightDto customerFlightDto) {
        return customerService.bookFlight(customerFlightDto);
    }
}
