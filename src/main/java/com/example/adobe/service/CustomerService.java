package com.example.adobe.service;

import com.example.adobe.dto.CustomerDto;
import com.example.adobe.entity.people.Customer;
import com.example.adobe.entity.people.People;
import com.example.adobe.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<Customer> insertCustomer(CustomerDto customerDto) {
        Customer newCustomer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(newCustomer);

        return ResponseEntity.ok(newCustomer);
    }
}
