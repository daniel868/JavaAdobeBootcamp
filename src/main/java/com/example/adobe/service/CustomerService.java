package com.example.adobe.service;

import com.example.adobe.domain.date_time.DateTimeDetails;
import com.example.adobe.dto.CustomerDto;
import com.example.adobe.dto.CustomerFlightDto;
import com.example.adobe.entity.flight.Flight;
import com.example.adobe.entity.flight.FlightType;
import com.example.adobe.entity.people.Customer;
import com.example.adobe.exception.CustomerNotFoundException;
import com.example.adobe.repository.CustomerRepository;
import com.example.adobe.repository.FlightRepository;
import com.example.adobe.service.date_time.FlightDateTimeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final FlightRepository flightRepository;
    private final FlightDateTimeService flightDateTimeService;

    private final SimpleDateFormat dateFormat;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, FlightRepository flightRepository, FlightDateTimeService flightDateTimeService, SimpleDateFormat dateFormat) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.flightRepository = flightRepository;
        this.flightDateTimeService = flightDateTimeService;
        this.dateFormat = dateFormat;
    }

    public ResponseEntity<Customer> insertCustomer(CustomerDto customerDto) {
        Customer newCustomer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(newCustomer);

        return ResponseEntity.ok(newCustomer);
    }

    public ResponseEntity<CustomerDto> getCustomerById(Long id) {
        CustomerDto customerDto = customerRepository.findById(id)
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .orElseThrow(() -> new CustomerNotFoundException("Could not find customer with id: " + id));

        return ResponseEntity.ok(customerDto);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Transactional(rollbackOn = SQLException.class)
    public ResponseEntity<CustomerFlightDto> bookFlight(CustomerFlightDto customerFlightDto) {

        Flight requestedFlight = flightRepository.getFlightByLocation(
                        customerFlightDto.getFromLocation(),
                        customerFlightDto.getToLocation())
                .stream()
                .findFirst()
                .orElseGet(() -> getRemoteFlight(customerFlightDto));


        Customer currentCustomer = customerRepository.findById(Long.valueOf(customerFlightDto.getClientId()))
                .orElseThrow(() -> new CustomerNotFoundException("Could not find a client matching your requirements"));

        currentCustomer.getBookedFlights().add(requestedFlight);

        customerRepository.save(currentCustomer);

        return ResponseEntity.ok(customerFlightDto);
    }

    private Flight getRemoteFlight(CustomerFlightDto customerFlightDto) {
        DateTimeDetails flightDateTime = flightDateTimeService.getFlightDateTime(
                customerFlightDto.getFromLocation(),
                customerFlightDto.getToLocation()
        );

        try {
            return Flight.builder()
                    .toLocation(customerFlightDto.getToLocation())
                    .fromLocation(customerFlightDto.getFromLocation())
                    .landingDateTime(dateFormat.parse(flightDateTime.getArr_time()))
                    .departureDateTime(dateFormat.parse(flightDateTime.getDep_time()))
                    .flightType(FlightType.COMMERCIAL_FLIGHT.getFlightType())
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
