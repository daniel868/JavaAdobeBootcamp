package com.example.adobe.service;

import com.example.adobe.domain.date_time.DateTimeDetails;
import com.example.adobe.domain.date_time.DateTimeResponse;
import com.example.adobe.dto.CustomerFlightDto;
import com.example.adobe.dto.CustomerDto;
import com.example.adobe.entity.flight.Flight;
import com.example.adobe.entity.flight.FlightType;
import com.example.adobe.entity.people.Customer;
import com.example.adobe.exception.CustomerNotFoundException;
import com.example.adobe.exception.FlightNotFoundException;
import com.example.adobe.repository.CustomerRepository;
import com.example.adobe.repository.FlightRepository;
import com.example.adobe.service.date_time.FlightDateTimeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final FlightRepository flightRepository;

    private final FlightDateTimeService flightDateTimeService;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, FlightRepository flightRepository, FlightDateTimeService flightDateTimeService) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.flightRepository = flightRepository;
        this.flightDateTimeService = flightDateTimeService;
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
                .orElseGet(() -> {
                    DateTimeDetails flightDateTime = flightDateTimeService.getFlightDateTime(
                            customerFlightDto.getFromLocation(),
                            customerFlightDto.getToLocation()
                    );

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

                    try {
                        return Flight.builder()
                                .toLocation(customerFlightDto.getToLocation())
                                .fromLocation(customerFlightDto.getFromLocation())
                                .landingDateTime(simpleDateFormat.parse(flightDateTime.getArr_time()))
                                .departureDateTime(simpleDateFormat.parse(flightDateTime.getDep_time()))
                                .flightType(FlightType.COMMERCIAL_FLIGHT.getFlightType())
                                .build();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });


        Customer currentCustomer = customerRepository.findById(Long.valueOf(customerFlightDto.getClientId()))
                .orElseThrow(() -> new CustomerNotFoundException("Could not find a client matching your requirements"));

        currentCustomer.getBookedFlights().add(requestedFlight);

        customerRepository.save(currentCustomer);

        return ResponseEntity.ok(customerFlightDto);
    }
}
