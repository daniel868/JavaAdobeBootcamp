package com.example.adobe.controller;

import com.example.adobe.dto.FlightDto;
import com.example.adobe.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/get-flights")
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/get-flights/{flightId}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable("flightId") Long flightId) {
        return flightService.getFlightById(flightId);
    }

    @PostMapping("/insert-flight")
    public void insertFlight(@RequestBody FlightDto flightDto) throws Exception {
        flightService.insertFlight(flightDto);
    }

    @DeleteMapping("/delete-flight/{flightId}")
    public void deleteFlight(@PathVariable("flightId") Long flightId) {
        flightService.deleteFlight(flightId);
    }
}
