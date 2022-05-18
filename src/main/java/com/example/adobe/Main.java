package com.example.adobe;

import com.example.adobe.entity.flight.Flight;
import com.example.adobe.entity.flight.FlightType;
import com.example.adobe.repository.CustomerRepository;
import com.example.adobe.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
@EnableAsync
public class Main implements CommandLineRunner {

    @Autowired
    CustomerRepository repository;

    @Autowired
    FlightRepository flightRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(String... args) {
        Flight flight = Flight.builder()
                .flightType(FlightType.COMMERCIAL_FLIGHT.getFlightType())
                .departureDateTime(Date.from(Instant.now().plus(2, ChronoUnit.DAYS)))
                .landingDateTime(Date.from(Instant.now().plus(3, ChronoUnit.DAYS)))
                .fromLocation("OTP")
                .toLocation("NYC")
                .build();

        flightRepository.save(flight);
    }
}
