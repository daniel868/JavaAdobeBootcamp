package com.example.adobe.service;

import com.example.adobe.dto.CustomerFlightDto;
import com.example.adobe.dto.CustomerDto;
import com.example.adobe.entity.flight.Flight;
import com.example.adobe.entity.people.Customer;
import com.example.adobe.exception.CustomerNotFoundException;
import com.example.adobe.exception.FlightNotFoundException;
import com.example.adobe.repository.CustomerRepository;
import com.example.adobe.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final FlightRepository flightRepository;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, FlightRepository flightRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.flightRepository = flightRepository;
    }

    public ResponseEntity<Customer> insertCustomer(CustomerDto customerDto) {
        Customer newCustomer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(newCustomer);

        return ResponseEntity.ok(newCustomer);
    }

    @Transactional(rollbackOn = SQLException.class)
    public ResponseEntity<CustomerFlightDto> bookFlight(CustomerFlightDto customerFlightDto) {

        Flight requestedFlight = flightRepository.getFlightByLocation(
                        customerFlightDto.getFromLocation(),
                        customerFlightDto.getToLocation())
                .stream()
                .findFirst()
                .orElseThrow(() -> new FlightNotFoundException("Could not find a flight matching your requirements"));

        Customer currentCustomer = customerRepository.findById(Long.valueOf(customerFlightDto.getClientId()))
                .orElseThrow(() -> new CustomerNotFoundException("Could not find a client matching your requirements"));

        currentCustomer.getBookedFlights().add(requestedFlight);

        customerRepository.save(currentCustomer);

        return ResponseEntity.ok(customerFlightDto);
    }
}
