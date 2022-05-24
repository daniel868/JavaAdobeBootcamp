package com.example.adobe.controller;

import com.example.adobe.dto.CustomerDto;
import com.example.adobe.dto.CustomerFlightDto;
import com.example.adobe.entity.people.Customer;
import com.example.adobe.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/get-customer/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/insert-customer")
    public ResponseEntity<Customer> insertNewCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.insertCustomer(customerDto);
    }

    @PostMapping("/book-flight")
    public ResponseEntity<CustomerFlightDto> bookFlight(@RequestBody CustomerFlightDto customerFlightDto) {
        return customerService.bookFlight(customerFlightDto);
    }

    @DeleteMapping("/delete-customer/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}
