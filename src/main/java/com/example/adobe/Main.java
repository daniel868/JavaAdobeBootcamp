package com.example.adobe;

import com.example.adobe.entity.flight.Flight;
import com.example.adobe.entity.flight.FlightType;
import com.example.adobe.entity.people.Customer;
import com.example.adobe.entity.people.People;
import com.example.adobe.repository.CustomerRepository;
import com.example.adobe.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    CustomerRepository repository;

    @Autowired
    FlightRepository flightRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Customer testCustomer = Customer.builder()
                .name("Customer1")
                .surname("Surname")
                .emailAddress("customer@gmail.com")
                .phoneNumber("074132131")
                .build();

        Flight flight = Flight.builder()
                .flightType(FlightType.COMMERCIAL_FLIGHT.getFlightType())
                .departureDateTime(Date.from(Instant.now().plus(2, ChronoUnit.DAYS)))
                .landingDateTime(Date.from(Instant.now().plus(3, ChronoUnit.DAYS)))
                .fromLocation("OTP")
                .toLocation("NYC")
                .build();


        testCustomer.getBookedFlights().add(flight);

        repository.save(testCustomer);
    }
}
