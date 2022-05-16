package com.example.adobe.repository;

import com.example.adobe.entity.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flight WHERE from_location=?1 AND to_location = ?2 ORDER BY departure_date_time ASC",
            nativeQuery = true)
    List<Flight> getFlightByLocation(String fromLocation, String toLocation);
}
